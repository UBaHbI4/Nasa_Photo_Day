package softing.ubah4ukdev.nasaphotoday.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import softing.ubah4ukdev.nasaphotoday.BuildConfig
import softing.ubah4ukdev.nasaphotoday.network.responses.ApodResponse
import softing.ubah4ukdev.nasaphotoday.network.responses.EpicResponse
import softing.ubah4ukdev.nasaphotoday.network.responses.MarsResponse

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.network

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
interface INasaApi {

    /**
    APOD -  Astronomy Picture of the Day

    Fields:
    - date A string in YYYY-MM-DD format indicating the date of the APOD image (example: 2014-11-03).
    Defaults to today's date. Must be after 1995-06-16, the first day an APOD picture was posted.
    There are no images for tomorrow available through this API.
    - concept_tags A boolean indicating whether concept tags should be returned with the rest of the response.
    The concept tags are not necessarily included in the explanation, but rather derived from common search tags
    that are associated with the description text. (Better than just pure text search.) Defaults to False.
    - hd A boolean parameter indicating whether or not high-resolution images should be returned.
    This is present for legacy purposes, it is always ignored by the service and high-resolution urls are returned regardless.
    - count A positive integer, no greater than 100. If this is specified then count randomly chosen images will be returned
    in a JSON array. Cannot be used in conjunction with date or start_date and end_date.
    - start_date A string in YYYY-MM-DD format indicating the start of a date range. All images in the range from start_date
    to end_date will be returned in a JSON array. Cannot be used with date.
    - end_date A string in YYYY-MM-DD format indicating that end of a date range. If start_date is specified without an
    end_date then end_date defaults to the current date.
    - thumbs If set to true, the API returns URL of video thumbnail. If an APOD is not a video, this parameter is ignored.

    Returned fields:
    - resource A dictionary describing the image_set or planet that the response illustrates, completely determined by the
    structured endpoint.
    - concept_tags A boolean reflection of the supplied option. Included in response because of default values.
    - title The title of the image.
    - date Date of image. Included in response because of default values.
    - url The URL of the APOD image or video of the day.
    - hdurl The URL for any high-resolution image for that day. Returned regardless of 'hd' param setting but will be
    omitted in the response IF it does not exist originally at APOD.
    - media_type The type of media (data) returned. May either be 'image' or 'video' depending on content.
    - explanation The supplied text explanation of the image.
    - concepts The most relevant concepts within the text explanation. Only supplied if concept_tags is set to True.
    - thumbnail_url The URL of thumbnail of the video.
    - copyright The name of the copyright holder.
    - service_version The service version used.
     */
    //Получить астрономическое фото дня
    @GET("planetary/apod?api_key=${BuildConfig.API_KEY}&concept_tags=True")
    fun getApod(
    ): Call<ApodResponse>

    //Получить фото Марса
    //opportunity,spirit,curiosity
    @GET("mars-photos/api/v1/rovers/curiosity/photos?api_key=${BuildConfig.API_KEY}")
    fun getMarsPicture(
        @Query("earth_date") date: String
    ): Call<MarsResponse>

    //Получить фото Земли
    //@GET("EPIC/api/natural/date/2021-07-09?api_key=${BuildConfig.API_KEY}")
    @GET("EPIC/api/natural?api_key=${BuildConfig.API_KEY}")
    fun getEarthPicture(
    ): Call<ArrayList<EpicResponse>>
}