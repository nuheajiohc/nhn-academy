import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movie implements Comparable<Movie> {
    private static final String[] kindOfInfo =
            {"MovieID", "Title", "KoreanTitle", "Plot", "ReleaseYear", "RunningTime", "GradeID", "GradeInKoreaID",
                    "Poster", "ReleaseDateInKorea", "BoxOfficeWWGross", "BoxOfficeUSGross", "Budget", "OriginalAuthor",
                    "OriginalSource"};
    private Map<String, String> movieInfo = new HashMap<>();

    public Movie(List<String> movieInfoList) {
        if (movieInfoList.size() != kindOfInfo.length) {
            throw new IllegalArgumentException("파싱이 제대로 이루어지지 않았습니다.");
        }

        for (int i = 0; i < kindOfInfo.length; i++) {
            this.movieInfo.put(kindOfInfo[i], movieInfoList.get(i));
        }
    }

    public String getInfo(String info) {
        return movieInfo.get(info);
    }

    public String getTitle(){
        return movieInfo.get("Title");
    }


    public void printMovieInfoToShell() {
        for (String info : kindOfInfo) {
            System.out.println(info + " : " + movieInfo.get(info));
        }
    }

    @Override
    public int compareTo(Movie anotherMovie) {
        return getTitle().compareTo(anotherMovie.getTitle());
    }
}
