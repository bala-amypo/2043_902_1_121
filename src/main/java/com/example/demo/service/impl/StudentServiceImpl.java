@Override
public Student addStudent(Student student) {

    if (student == null) {
        throw new IllegalArgumentException("Student cannot be null");
    }

    if (student.getRollNumber() == null || student.getRollNumber().isBlank()) {
        throw new IllegalArgumentException("Roll number is required");
    }

    if (student.getName() == null || student.getName().isBlank()) {
        throw new IllegalArgumentException("Name is required");
    }

    if (student.getDepartment() == null || student.getDepartment().isBlank()) {
        throw new IllegalArgumentException("Department is required");
    }

    if (student.getYear() == null || student.getYear() < 1 || student.getYear() > 4) {
        throw new IllegalArgumentException("Invalid year");
    }

    return repo.save(student);
}
