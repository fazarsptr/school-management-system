package com.mtsarridho.config;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

/** Creates a local account interactively. Run from a real terminal, not the IDE output pane. */
public final class AdminSetup {
    private AdminSetup() {}

    public static void main(String[] args) throws Exception {
        if (args.length != 1 || !args[0].matches("[a-zA-Z0-9._-]{3,50}")) {
            System.err.println("Usage: java ... com.mtsarridho.config.AdminSetup <username>");
            return;
        }
        Console console = System.console();
        if (console == null) {
            System.err.println("Run this command in CMD/Terminal to enter the password securely.");
            return;
        }
        char[] first = console.readPassword("New password: ");
        char[] second = console.readPassword("Confirm password: ");
        try {
            if (first == null || second == null || first.length < 12 || !Arrays.equals(first, second)) {
                System.err.println("Passwords must match and contain at least 12 characters.");
                return;
            }
            String hashed = PasswordHasher.hash(first);
            Connection conn = KoneksiDatabase.getKoneksi();
            try (PreparedStatement check = conn.prepareStatement("SELECT id FROM users WHERE username = ?")) {
                check.setString(1, args[0]);
                try (ResultSet results = check.executeQuery()) {
                    if (results.next()) {
                        System.err.println("Account already exists. No account was modified.");
                        return;
                    }
                }
            }
            try (PreparedStatement insert = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
                insert.setString(1, args[0]);
                insert.setString(2, hashed);
                insert.executeUpdate();
                System.out.println("Local account created.");
            }
        } finally {
            if (first != null) Arrays.fill(first, '\0');
            if (second != null) Arrays.fill(second, '\0');
        }
    }
}
