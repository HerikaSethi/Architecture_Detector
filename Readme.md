# Architecture Detector in Java

This Java software scans a folder path to count and identify the architecture type of shared library files (`.so`). It detects the architecture based on the ELF (Executable and Linkable Format) header.

## Supported Architectures:
1. **ARM** - Commonly used in Android physical devices (e.g., APK builds).
2. **x86** - Used by emulators.
3. **MIPS** - Designed for legacy systems.

## What Are Valid Library Files?
- Only `.so` (shared library) files are considered for architecture detection.
- Files with extensions such as `.txt`, `.cpp`, and `.md` are **ignored**.
- Some `.so` files may not necessarily be binary, so validation is essential.

### Validating `.so` Files:
- Ensure the file ends with the `.so` extension.
- Verify if the file follows the **Executable and Linkable Format (ELF)**.

### What is ELF?
- **ELF** (Executable and Linkable Format) is a format used to store executable code and shared libraries. It is crucial for Android, especially for native code execution.

- Each ELF file starts with a specific "magic number" (indicating it's an ELF file), and the header holds the architecture type information.

### Architecture Detection Process:
1. Open each `.so` file in binary mode.
2. Check the first few bytes to ensure the file is a valid ELF.
3. Extract the architecture information from bytes 18 and 19 (e_machine field in the ELF header).
4. Map the `e_machine` value to the corresponding architecture.

#### Architecture Mapping:

| Architecture      | Hex Code | Decimal |
| ----------------- | -------- | ------- |
| ARM               | `0x28`   | 40      |
| AARCH64 (ARM64)   | `0xB7`   | 183     |
| x86 (Intel 80386) | `0x03`   | 3       |
| x86_64            | `0x3E`   | 62      |
| MIPS              | `0x08`   | 8       |

## Running the Software

### 1. **Running via Command Line (CMD)**

- Open your command prompt.
- Compile the Java file with:
  ```bash
  javac your_filename.java
- Run the program using:
   ```bash
  java your_filename

Example:

javac ArchitectureDetector.java

java ArchitectureDetector

- When prompted, enter the path to the folder containing the .so files.

Example : C:/Users/Desktop/JavaArchitectureDetector/sample_libs

(C:/Users/Desktop/folder_name/extracted_folder)

### 2. **Running via Makefile**
Prerequisites:

- Install make using Chocolatey:
  ```bash
  choco install make
- Use the following command to run the program:
  ```bash
  make run
Folder Input:
When prompted, enter the folder path where the shared libraries are located. 

Example:

C:/Users/Desktop/JavaArchitectureDetector/sample_libs


### 3. **Running via Docker**
Prerequisites:
- Ensure that Docker Desktop is installed and running.
- Build the Docker Image:
- Navigate to the folder with the Dockerfile and run:
  ```bash
  docker build -t architecture-detector .
- Run the Docker Container:
- Start the container with the following command:
  ```bash
  docker run -it --rm -v "C:/Users/Desktop/JavaArchitectureDetector/sample_libs:/app/libs" architecture-detector
- Folder Path Input:
- When asked for the folder path, enter:
  ```bash
  /app/libs

Example:

docker build -t architecture-detector .

docker run -it --rm -v "C:/Users/Desktop/JavaArchitectureDetector/sample_libs:/app/libs" architecture-detector

Enter the folder path when prompted.

/app/libs