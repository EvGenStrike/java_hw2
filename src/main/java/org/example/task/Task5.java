package org.example.task;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task5 implements Task {
    private final Path sourceDir;
    private final Path targetDir;
    private final ExecutorService executor;
    private final AtomicBoolean running;

    public Task5(String sourcePath, String targetPath) {
        this.sourceDir = Paths.get(sourcePath);
        this.targetDir = Paths.get(targetPath);
        this.executor = Executors.newSingleThreadExecutor();
        this.running = new AtomicBoolean(false);
    }

    @Override
    public void start() {
        if (running.compareAndSet(false, true)) {
            executor.submit(this::syncFolders);
        } else {
            System.out.println("Синхронизация уже запущена.");
        }
    }

    @Override
    public void stop() {
        if (running.compareAndSet(true, false)) {
            executor.shutdownNow();
            System.out.println("Синхронизация остановлена.");
        } else {
            System.out.println("Синхронизация не выполняется.");
        }
    }

    private void syncFolders() {
        try {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (!running.get()) return FileVisitResult.TERMINATE;

                    Path targetFile = targetDir.resolve(sourceDir.relativize(file));
                    if (Files.notExists(targetFile) || !Files.isSameFile(file, targetFile)) {
                        Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Синхронизирован файл: " + file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (!running.get()) return FileVisitResult.TERMINATE;

                    Path targetSubDir = targetDir.resolve(sourceDir.relativize(dir));
                    if (Files.notExists(targetSubDir)) {
                        Files.createDirectories(targetSubDir);
                        System.out.println("Создана директория: " + targetSubDir);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Синхронизация завершена.");
        } catch (IOException e) {
            System.err.println("Ошибка при синхронизации: " + e.getMessage());
        }
    }
}