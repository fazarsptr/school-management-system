# School Management System

A Java Swing-based School Management System for managing student records, teachers, attendance, classes, subjects, and reports using MySQL.

## Features

- Student and teacher records management
- Teacher attendance tracking
- Classes, homeroom assignments, and subject management
- Dashboard and JasperReports reporting screens
- Database-backed login using PBKDF2 password hashing

## Teacher Attendance Reporting

The teacher attendance module (`FormDataAbsensi`) provides comprehensive reporting, interactive filtering, and export capabilities for faculty attendance tracking:

### Implemented Features

- **Month and Year Filtering (`Rekap Bulanan`)**: Select a specific month and year via dropdowns and click **FILTER** to load records for that period. Click **SEMUA** to reset the date filter and view all historical records.
- **Teacher Name Search (`Cari Guru`)**: Search attendance records by teacher name with partial string matching (`LIKE %keyword%`). Trigger search by clicking **CARI** or pressing Enter in the search field.
- **Attendance Status Filtering (`Status`)**: Filter records by attendance category:
  - `Semua Status` (All records)
  - `Hadir` (Present)
  - `Tidak Hadir` (Absent)
  Selecting a status instantly filters the displayed attendance list.
- **Filtered Attendance Totals (`Data Terfilter`)**: Displays immediate badge counters reflecting the visible records in the table:
  - **Total Data**: Total records matching the active filter criteria.
  - **Hadir**: Count of present records in the filtered view.
  - **Tidak Hadir**: Count of absent records in the filtered view.
  Counters update dynamically upon any filter change and reset to zero when no records match.
- **Monthly Attendance Summary Cards**: Visual overview cards for the selected month and year:
  - **TOTAL HADIR**: Total present count for the month.
  - **TOTAL TIDAK HADIR**: Total absent count for the month.
  - **PERSENTASE**: Monthly attendance rate calculated from database records.
- **Empty-State Handling**: If search or filter parameters yield zero matching records:
  - Displays a clear notice banner: *"Tidak ada data absensi yang sesuai dengan filter."*
  - Automatically clears stale rows from the table view.
  - Banner hides automatically as soon as matching records become available.
  - Database or SQL errors are distinguished from empty results, showing an error dialog rather than an empty-state message.
- **CSV Export (`EXPORT CSV`)**: Exports the currently filtered attendance dataset (Teacher Name, Attendance Status, Date) to a Microsoft Excel-compatible CSV file:
  - Prompts the user with a `JFileChooser` and suggests default filenames (`teacher_attendance_YYYY_MM.csv`).
  - Confirms before overwriting existing files.
  - Handles CSV escaping (commas, quotation marks, line breaks) safely.

### How to Use

1. Navigate to the **Data Absensi Guru** screen from the application menu.
2. **Filter by Period**: Select the desired month from the **Bulan** dropdown and year from the **Tahun** dropdown, then click **FILTER**.
3. **Filter by Name or Status**: Enter a partial or full teacher name in the **Cari Guru** field and/or select a status from the **Status** dropdown. Click **CARI** or press Enter. To clear filters, click **RESET** or **SEMUA**.
4. **View Statistics**: Check the **Data Terfilter** badges for instant visible counts or the summary cards on the right for monthly percentage aggregates.
5. **Export to CSV**: Click **EXPORT CSV**, choose a target directory, specify or confirm the filename, and save.

> **Note on Verification**: All reporting logic, SQL parameterization, Swing layouts, and helper routines are fully implemented in code and verified via static analysis and Git diff inspections. Full end-to-end runtime verification requires a configured local MySQL instance and desktop Swing environment (see [Local setup](#local-setup)).

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
