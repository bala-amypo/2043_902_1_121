@Override
public ExamSession createSession(ExamSession session) {

    if (session == null) {
        throw new IllegalArgumentException("Session cannot be null");
    }

    if (session.getExamDate() == null || session.getExamDate().isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("Exam date must be today or future");
    }

    if (session.getStudents() == null || session.getStudents().isEmpty()) {
        throw new IllegalArgumentException("Session must have students");
    }

    return repo.save(session);
}
