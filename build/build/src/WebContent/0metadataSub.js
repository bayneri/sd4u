/**
 * swe510 concepts
 * v20150228
 */

/*
 * Bu format kullanim acisindna cok daha kolay;
 * - #END unutmalari onluyor.
 * - eclipse js editorunden dolayi 
 * -- sub lari fonksiyon olarak goruyor. 
 * -- Gerektikce isi biten sub lari kapamak mumkun.
 * -- eclipse outline kullanilabiliyor. (sirali veya file sirasi)
 * -- renklendirme var
 * 
 * Yapilacaklar
 * - js comment yapisini desteklemek
 * - directory ve toc basligini asagidaki formattan okumak.
 * - sub call formati degisiyor. *sub yerine sonu() ile bitenler
 */

/**
 * sd4u platform info in short
 */
function sd4uPlatformInfo() {
	dir = "sd4u/info";
	toc = "sd4u Platform Info";
	sepSd4uInfo.html;
	sd4uInfoScreen.html;
	sd4uInfo.html;
	sepSd4uInfoEnd.html;
}

/**
 * Course Information: swe510
 */
function swe510CourseInfo(){
	dir = "swe510/courseInfo";
	toc = "General info about the course";
	sepCourseInfo.html;
	courseWhoIsWho.html;
	courseInfo.html;	
	DetailedModelB.html;
	sd4uPlatformInfo();
	courseWebSite.html;
	courseAim.html;
//	courseTextbook.html;
//	courseGrading.html;
	courseGradingWarning.html;
	aaa(); // Bu function tanimli degil. Bunu nasil fark edecegiz?
	courseDates.html;
	sepCourseInfoEnd.html;
}
