public class Review {
    private int ID;
    private String StudentID;
    private String CourseID;
    private String review_message;
    private int rating;
    public Review(int ID, String studentID, String courseID, String review_message, int rating){
        this.CourseID = courseID;
        this.ID = ID;
        this.review_message = review_message;
        this.rating = rating;
        this.StudentID = studentID;

    }
    public int getID(){
        return this.ID;
    }
    public String getCourseID(){
        return this.CourseID;
    }
    public String getStudentID(){
        return this.StudentID;
    }
    public String getReview_message(){
        return this.review_message;
    }
    public int getRating(){
        return this.rating;
    }
}
