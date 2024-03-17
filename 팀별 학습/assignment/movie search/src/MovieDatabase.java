import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieDatabase {

    private List<Movie> movieDatabase = new ArrayList<>();

    private void addMovie(List<String> processedMovieInfo) {
        movieDatabase.add(new Movie(processedMovieInfo));
    }

    public void parse(List<String> readingFile) {
        String pattern = "^(?:\".*?\"|(?!\"|.*\"$).*)$";

        for (String oneLine : readingFile) {
            List<String> processedMovieInfo = parseOneLine(oneLine, pattern);
            try {
                addMovie(processedMovieInfo);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        Collections.sort(movieDatabase);
    }

    private List<String> parseOneLine(String oneLine, String pattern) {
        String findDoubleQuotation = "^\"|\"$";

        String[] splitedOneLineInfo = oneLine.split(",");
        List<String> processedMovieInfo = new ArrayList<>();

        StringBuilder tokenInfo = new StringBuilder();
        for (String info : splitedOneLineInfo) {
            tokenInfo.append(info);
            if (tokenInfo.toString().matches(pattern)) {
                processedMovieInfo.add(tokenInfo.toString().replaceAll(findDoubleQuotation, ""));
                tokenInfo.setLength(0);
                continue;
            }
            tokenInfo.append(",");
        }
        return processedMovieInfo;
    }


    public List<Movie> getMovies(String movieTitle) {
        List<Movie> movieList = new ArrayList<>();
        int movieIndex = binarySearch(movieTitle);

        boolean hasNoMovieTitle = !movieDatabase.get(movieIndex).getTitle().equals(movieTitle);
        if (hasNoMovieTitle) {
            throw new IllegalArgumentException("영화가 존재하지 않습니다.");
        }

        boolean hasDuplicateMovieTitle;
        do {
            movieList.add(movieDatabase.get(movieIndex));
            movieIndex++;
            hasDuplicateMovieTitle = movieDatabase.get(movieIndex).getTitle().equals(movieTitle);
        } while (hasDuplicateMovieTitle);

        return movieList;
    }


    private int binarySearch(String movieTitle) {
        int start = 0;
        int end = movieDatabase.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            boolean isMoreThanMovieTitle = movieDatabase.get(mid).getTitle().compareTo(movieTitle) >= 0;
            if (isMoreThanMovieTitle) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
