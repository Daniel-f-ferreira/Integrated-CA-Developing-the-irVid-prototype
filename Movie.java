package com.locadora.locadora.Models;

import java.util.LinkedList;

public class Movie implements Comparable<Movie> {

    private boolean adult;
    private BelongToCollection belongsToCollection;
    private int budget;
    private LinkedList<Genre> genres;
    private String homepage;
    private int id;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String popularity;
    private String posterPath;
    private LinkedList<ProductionCompany> productionCompanies;
    private LinkedList<ProductionCountry> productionCountries;
    private String releaseDate;
    private float revenue;
    private float runtime;
    private LinkedList<SpokenLanguage> spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private float voteAverage;
    private int voteCount;
    private LinkedList<Rent> rents;
    private int totalRents = 0;
    private float price;

    public Movie(boolean adult, BelongToCollection belongsToCollection, int budget, LinkedList<Genre> genres,
            String homepage, int id, String imdbId, String originalLanguage, String originalTitle, String overview, String popularity,
            String posterPath, LinkedList<ProductionCompany> productionCompanies, LinkedList<ProductionCountry> productionCountries,
            String releaseDate, float revenue, float runtime, LinkedList<SpokenLanguage> spokenLanguages, String status, String tagline,
            String title, boolean video, float voteAverage, int voteCount) {
        this.adult = adult;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.rents = new LinkedList<>();
        this.totalRents = 0;
        this.price = 3.f;
    }
    
    
    public void setPrice(float price)
    {
        this.price = price;
        
    }
    
    public float getPrice()
    {
        return this.price;
    }

    public Movie() {
    }

    public int getTotalRents()
    {
        return this.totalRents;
    }
    public void setTotalRents(int totalRents)
    {
        this.totalRents = totalRents;
    }
    
    @Override
    public int compareTo(Movie movie) {
        return this.totalRents - movie.totalRents;

    }

    public LinkedList<Rent> getRents() {
        return this.rents;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public BelongToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(BelongToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public LinkedList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(LinkedList<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public LinkedList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(LinkedList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public LinkedList<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(LinkedList<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public float getRuntime() {
        return runtime;
    }

    public void setRuntime(float runtime) {
        this.runtime = runtime;
    }

    public LinkedList<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(LinkedList<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

}
