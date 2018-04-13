package model;

    /**
     * @author Ivan A. Reffatti
     */
    public class JavaMage {

        private final String firstName;

        private final String surName;

        private final Integer age;

        private String fatherName;

        public JavaMage(final String firstName, final String surName, final Integer age) {
            this.firstName = firstName;
            this.surName = surName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getSurName() {
            return surName;
        }

        public Integer getAge() {
            return age;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public static JavaMage create() {
            return new JavaMage("Gandalf", "The Gray", 1000);
        }
    }
