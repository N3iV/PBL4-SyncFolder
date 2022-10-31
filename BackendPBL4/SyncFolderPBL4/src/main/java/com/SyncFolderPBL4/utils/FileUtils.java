package com.SyncFolderPBL4.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.SyncFolderPBL4.model.entities.FileEntity;

public class FileUtils {
	private static Random rd = new Random();
	public static final byte[] BUFFER = new byte[1024];

	private FileUtils() {

	}

	public static File zippingFile(FileEntity fileEntity, String sourcePath) {
		File outputZipFile = new File(
				new File(sourcePath).getParentFile().toString() + File.separator + fileEntity.getName() + ".zip");

		File inputFolder = new File(sourcePath);
		List<File> files = listChildFiles(inputFolder);
		ZipOutputStream zos = null;
		FileInputStream fis = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(outputZipFile));
			for (File file : files) {
				String filePath = file.getAbsolutePath();
				System.out.println("Zipping " + filePath);
				String entryName = file.getName();
				ZipEntry ze = new ZipEntry(entryName);
				zos.putNextEntry(ze);
				fis = new FileInputStream(filePath);
				int len;
				while ((len = fis.read(BUFFER)) > 0) {
					zos.write(BUFFER, 0, len);
				}
				close(fis);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(zos, fis);
		}

		return outputZipFile;
	}

	public static List<File> listChildFiles(File inputFile) {
		List<File> result = new ArrayList<>();
		File[] files = inputFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				result.add(file);
			} else {
				List<File> filesTemp = listChildFiles(file);
				result.addAll(filesTemp);
			}
		}
		return result;
	}

	public static String getPathProject(String source) {
		String nameProject = (new File(source)).getName();
		return source.replace(source.substring(source.indexOf(".metadata"), source.lastIndexOf(nameProject)), "");
	}

	public static Path copy(String sourcePathName, String targetPathName) {
		Path source = Paths.get(sourcePathName);
		Path target = createNewDirAsPath(targetPathName);

		Path targetPath = null;
		try {
			targetPath = Files.copy(source, target.resolve(renameTo(source.getFileName())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return targetPath;
	}

	public static Path renameTo(Path source) {
		String filename = source.toString();
		String renameTo = "rnf" + System.currentTimeMillis() + new Random().nextInt(100)
				+ filename.substring(filename.lastIndexOf("."));
		return Paths.get(renameTo);
	}

	public static File createNewFile(File parent, String child) {
		return createNewFile(parent.getAbsoluteFile() + File.separator + child);
	}

	public static Path createNewFileAsPath(String pathname) {
		return createNewFile(pathname).toPath();
	}

	public static File createNewFile(String pathname) {
		File file = new File(pathname);
		File parent = createNewDir(file.getParent());
		if (parent.isDirectory()) {
			if (!file.exists()) {
				try {
					boolean flag = file.createNewFile();
					if (flag) {
						System.out.println("Create file " + file.getName() + "successful. ");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("File " + file.getName() + " already existed");
			}
		}
		return file;
	}

	public static Path createNewDirAsPath(String pathname) {
		return createNewDir(pathname).toPath();
	}

	public static File createNewDir(String pathname) {
		File dir = new File(pathname);
		if (!dir.exists()) {
			boolean flag = dir.mkdirs();
			if (flag) {
				System.out.println("Folder " + dir.getName() + " created successful.");
			}
		} else {
			System.out.println("Folder " + dir.getName() + " already existed");
		}
		return dir;
	}

	public static void close(AutoCloseable... closeables) {
		for (AutoCloseable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
