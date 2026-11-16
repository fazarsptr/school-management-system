# School Management System

A Java Swing-based School Management System for managing student records, teachers, attendance, classes, subjects, and reports using MySQL.

## Features

- Student and teacher records management
- Teacher attendance tracking
- Classes, homeroom assignments, and subject management
- Dashboard and JasperReports reporting screens
- Database-backed login using PBKDF2 password hashing

## Technology

Java Swing (Java 8 source compatibility), Apache NetBeans/Ant, MySQL or MariaDB, JDBC, and JasperReports.

## Project structure

```text
config/                  Configuration templates (database.properties.example)
database/                Database structure definitions (schema.sql)
lib/                     External library directory (dependency JARs)
nbproject/               NetBeans project configuration files
src/                     Java source code, .form GUI files, icons, report templates
build.xml                Apache Ant build script
manifest.mf              JAR manifest configuration
README.md                Project documentation
```

## Local setup

1. Install JDK 8+ and Apache NetBeans with Ant, and start MySQL/MariaDB.
2. Import `database/schema.sql` into a **separate local development database** using MySQL or phpMyAdmin. This schema creates no user accounts and contains no real records.
3. Copy `config/database.properties.example` to `config/database.properties`. Set `db.url`, `db.username`, and `db.password` for your own local database. This file is excluded by `.gitignore`.
4. In NetBeans, open the project folder. Supply the dependency JARs under `lib/` as described below. Run **Clean and Build**.
5. Initialize an administrator account using a normal system terminal (not an IDE console) after building:

   **Windows (CMD / PowerShell):**
   ```bat
   java -cp "build\classes;lib\*" com.mtsarridho.config.AdminSetup admin
   ```

   **Linux / macOS:**
   ```bash
   java -cp "build/classes:lib/*" com.mtsarridho.config.AdminSetup admin
   ```
   You will be prompted to enter and confirm a password (at least 12 characters). It is not stored in plaintext.
6. Start the application from NetBeans using the `com.mtsarridho.form.FormLogin` main class.

**Database compatibility:** The previous exported database contains a plaintext legacy password and actual student/teacher data. Do **not** import it into a public/demo environment. This cleaned version does not auto-convert legacy passwords; create new accounts in a fresh database.

## Dependencies

The original project uses external libraries including MySQL Connector/J, NetBeans AbsoluteLayout, JCalendar, JasperReports 5.6, Apache Commons, Groovy, iText, and the RojeruSan UI library. Local library paths are now relative to `lib/` instead of pointing to another developer's PC.

**GitHub/public distribution:** Third-party binaries in `lib/*.jar` are intentionally ignored by Git until the redistribution terms of each dependency have been verified. Restore compatible libraries from your authorized local installation or the original project ZIP before building. If a dependency is missing or incompatible, the build may fail. Report templates and UI sources are preserved.

## Security

- Database credentials are loaded from ignored local properties or environment variables (`SCHOOL_DB_URL`, `SCHOOL_DB_USER`, `SCHOOL_DB_PASSWORD`).
- Authentication uses database-backed PBKDF2-SHA256 hashes; no shared default password is committed.
- The public SQL schema includes no actual names, identity numbers, dates of birth, attendance records, or user credentials.
- Do not commit real database exports, screenshots of personal data, or files under `nbproject/private/`.
- Review the application's remaining SQL and error handling before deploying in a real school environment. This is a portfolio/educational desktop application, not an audited production system.

## Screenshots

Add screenshots captured with entirely fictional demonstration data. Do not upload screenshots containing real student or teacher identities.

## Known limitations

- Requires local database and external dependencies.
- The provided schema is empty; no operational records are included.
- Requires manual runtime and GUI testing in NetBeans on Windows.
- This cleanup improves login and configuration but is not a full application-wide security audit.
