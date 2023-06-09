package com.example.movielibrary.APIMovie;

import android.database.Cursor;
import android.util.Log;

import com.example.movielibrary.DatabaseHelper;
import com.example.movielibrary.PageViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class qui permet de géré l'API
 */
public class MovieAPIView {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "d8ca27475e9a87d4db474ea21042e5af";

    /**
     * Méthode qui permet de récupérer les films
     * @param page la page à récupérer
     * @param pageViewModel le pageViewModel
     */
    public static void getMoviePages(int page, PageViewModel pageViewModel) {
        ArrayList<BasicMovie> dataset = new ArrayList<>();

        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les films
        Call<MovieResult> call = movieApi.getAllMovies(API_KEY, page, pageViewModel.getLanguage());

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, retrofit2.Response<MovieResult> response) {
                if (response.isSuccessful()) {

                    MovieResult results = response.body();
                    List<BasicMovie> movies = results.getMovies();

                    pageViewModel.setTotalPages(results.getTotalPages());

                    dataset.addAll(movies);

                    // On ajoute le dataset à la pageViewModel
                    pageViewModel.setMovieList(dataset);
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    /**
     * Méthode qui permet de récupérer le film en particulier
     * @param movieId l'id du film à récupérer
     * @param pageViewModel le pageViewModel
     */
    public static void getMovie(int movieId, PageViewModel pageViewModel) {
        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les films
        Call<Movie> call = movieApi.getMovie(movieId, API_KEY, pageViewModel.getLanguage());

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, retrofit2.Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();

                    // On ajoute le dataset à la pageViewModel
                    pageViewModel.setMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    /**
     * Méthode qui permet de récupérer les acteurs d'un film
     * @param movieId l'id du film à récupérer
     * @param pageViewModel le pageViewModel
     */
    public static void getMovieCredits(int movieId, PageViewModel pageViewModel) {
        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les films
        Call<CreditsResult> call = movieApi.getCredits(movieId, API_KEY);

        call.enqueue(new Callback<CreditsResult>() {
            @Override
            public void onResponse(Call<CreditsResult> call, retrofit2.Response<CreditsResult> response) {
                if (response.isSuccessful()) {
                    Actor[] actor = response.body().getCast();

                    // On ajoute le dataset à la pageViewModel
                    pageViewModel.setActorList(actor);
                }
            }

            @Override
            public void onFailure(Call<CreditsResult> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    /**
     * Méthode qui permet de récupérer les films en fonction d'une recherche
     * @param page la page à récupérer
     * @param query la recherche
     * @param pageViewModel le pageViewModel
     */
    public static void searchMovie(int page, String query, PageViewModel pageViewModel) {
        ArrayList<BasicMovie> dataset = new ArrayList<>();

        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les films
        Call<MovieResult> call = movieApi.searchMovie(API_KEY, page, query, pageViewModel.getLanguage());

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, retrofit2.Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    MovieResult results = response.body();
                    List<BasicMovie> movies = results.getMovies();

                    pageViewModel.setTotalPages(results.getTotalPages());

                    dataset.addAll(movies);

                    // On ajoute le dataset à la pageViewModel
                    pageViewModel.setMovieList(dataset);
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    /**
     * Méthode qui permet de récupérer les genres de films
     * @param pageViewModel le pageViewModel
     */
    public static void getGenres(PageViewModel pageViewModel) {
        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les genres
        Call<GenresResult> call = movieApi.getGenres(API_KEY, pageViewModel.getLanguage());

        call.enqueue(new Callback<GenresResult>() {
            @Override
            public void onResponse(Call<GenresResult> call, retrofit2.Response<GenresResult> response) {
                if (response.isSuccessful()) {
                    Genre[] genres = response.body().getGenres();

                    // On ajoute le dataset à la pageViewModel
                    pageViewModel.setGenreList(genres);
                }
            }

            @Override
            public void onFailure(Call<GenresResult> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    /**
     * Méthode qui permet de récupérer les films en fonction d'un genre, et de date
     */
    public static void getMoviesWithFilter(int page, int genreId, int year1, int year2, PageViewModel pageViewModel) {
        ArrayList<BasicMovie> dataset = new ArrayList<>();

        // On crée la date
        String date1 = year1 + "-01-01";
        String date2 = year2 + "-12-31";

        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les films
        Call<MovieResult> call;
        if (genreId != -1)
            call = movieApi.getMovieFiltred(API_KEY, genreId, page, date1, date2, pageViewModel.getLanguage());
        else
            call = movieApi.getMovieFiltredYear(API_KEY, page, date1, date2, pageViewModel.getLanguage());
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, retrofit2.Response<MovieResult> response) {
                if (response.isSuccessful()) {
                    MovieResult results = response.body();
                    List<BasicMovie> movies = results.getMovies();

                    pageViewModel.setTotalPages(results.getTotalPages());

                    dataset.addAll(movies);

                    // On ajoute le dataset à la pageViewModel
                    pageViewModel.setMovieList(dataset);
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    /**
     * Méthode qui permet de récupérer un film en fonction de son id et le mettre dans le pageViewModel
     * @param movieId
     * @param pageViewModel
     */
    public static void getMovieToList(int movieId, PageViewModel pageViewModel) {
        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les films
        Call<Movie> call = movieApi.getMovie(movieId, API_KEY, pageViewModel.getLanguage());

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, retrofit2.Response<Movie> response) {
                if (response.isSuccessful()) {
                    Movie movie = response.body();

                    ArrayList<BasicMovie> dataset = new ArrayList<>();

                    // Ajoute tous les films du pageViewModel au dataset
                    dataset = pageViewModel.getMovieList().getValue();

                    BasicMovie movie1 = new BasicMovie(movie.getId(), movie.getTitle(), movie.getVote_average());

                    dataset.add(movie1);

                    pageViewModel.setMovieList(dataset);

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    public static void getActor(int actorId, PageViewModel pageViewModel) {
        // Créez une instance de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface MovieApi
        MovieAPI movieApi = retrofit.create(MovieAPI.class);

        // Appel de la méthode pour récupérer les films
        Call<ActorDetail> call = movieApi.getActor(actorId, API_KEY, pageViewModel.getLanguage());

        call.enqueue(new Callback<ActorDetail>() {
            @Override
            public void onResponse(Call<ActorDetail> call, retrofit2.Response<ActorDetail> response) {
                if (response.isSuccessful()) {
                    ActorDetail actor = response.body();

                    pageViewModel.setActorDetail(actor);
                }
            }

            @Override
            public void onFailure(Call<ActorDetail> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }
}
