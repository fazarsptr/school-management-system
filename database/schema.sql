-- Public database schema only. Contains NO real student, teacher, or user records.
-- Import into a dedicated development database. No default account is seeded.
CREATE DATABASE IF NOT EXISTS `db_managesekolah` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_managesekolah`;

CREATE TABLE `tb_guru` (
  `nik` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_kehadiran_guru` (
  `id_kehadiran` int(20) NOT NULL,
  `nik` varchar(20) DEFAULT NULL,
  `nama` varchar(50) NOT NULL,
  `kehadiran` varchar(25) NOT NULL,
  `waktu` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_mapel` (
  `id_mapel` int(11) NOT NULL,
  `nik_guru` varchar(20) DEFAULT NULL,
  `nama_mapel` varchar(100) NOT NULL,
  `kelas` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_rombel` (
  `id_rombel` int(11) NOT NULL,
  `nama_rombel` varchar(50) NOT NULL,
  `wali_kelas` varchar(20) DEFAULT NULL,
  `tingkat_kelas` varchar(10) NOT NULL,
  `tahun_ajaran` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_rombel_siswa` (
  `id` int(11) NOT NULL,
  `id_rombel` int(11) NOT NULL,
  `nisn` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_siswa` (
  `nisn` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `tempat_lahir` varchar(50) NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `tingkat` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `tb_guru`
  ADD PRIMARY KEY (`nik`);

ALTER TABLE `tb_kehadiran_guru`
  ADD PRIMARY KEY (`id_kehadiran`),
  ADD KEY `nik` (`nik`);

ALTER TABLE `tb_mapel`
  ADD PRIMARY KEY (`id_mapel`),
  ADD KEY `nik_guru` (`nik_guru`);

ALTER TABLE `tb_rombel`
  ADD PRIMARY KEY (`id_rombel`),
  ADD KEY `wali_kelas` (`wali_kelas`),
  ADD KEY `wali_kelas_2` (`wali_kelas`);

ALTER TABLE `tb_rombel_siswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_rombel` (`id_rombel`),
  ADD KEY `nisn` (`nisn`);

ALTER TABLE `tb_siswa`
  ADD PRIMARY KEY (`nisn`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

ALTER TABLE `tb_kehadiran_guru`
  MODIFY `id_kehadiran` int(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tb_mapel`
  MODIFY `id_mapel` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tb_rombel`
  MODIFY `id_rombel` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tb_rombel_siswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tb_mapel`
  ADD CONSTRAINT `tb_mapel_ibfk_1` FOREIGN KEY (`nik_guru`) REFERENCES `tb_guru` (`nik`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `tb_rombel`
  ADD CONSTRAINT `tb_rombel_ibfk_1` FOREIGN KEY (`wali_kelas`) REFERENCES `tb_guru` (`nik`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `tb_rombel_siswa`
  ADD CONSTRAINT `tb_rombel_siswa_ibfk_1` FOREIGN KEY (`id_rombel`) REFERENCES `tb_rombel` (`id_rombel`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_rombel_siswa_ibfk_2` FOREIGN KEY (`nisn`) REFERENCES `tb_siswa` (`nisn`) ON DELETE SET NULL ON UPDATE CASCADE;
