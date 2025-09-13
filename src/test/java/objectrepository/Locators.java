package objectrepository;

import org.openqa.selenium.By;

public class Locators {
	
	//click login button

	
	public static By loginbtn = By.xpath("/html/body/main/div[1]/div/div[2]/div[2]/div/div/div");
	
	//click phone number 
	
	public static By clkphoneNumber = By.xpath("//input[@placeholder='Enter Mobile Number']");
	
	public static By clkcontinuebtn = By.xpath("//*[@id=\"portal-root\"]/div/div[2]/div/div/div/div[1]/button");
	
	public static By verifybtn = By.xpath("//button[text()=\"Verify\"]");
	
	public static By closebutton = By.xpath("//*[@id=\"closeButton\"]/span");
	
	public static By clkotp1 = By.xpath("//*[@id=\"otp\"]/div/div[1]");
	public static By clkotp2 = By.xpath("//*[@id=\"otp\"]/div/div[2]");
	public static By clkotp3 = By.xpath("//*[@id=\"otp\"]/div/div[3]");
	public static By clkotp4 = By.xpath("//*[@id=\"otp\"]/div/div[4]");
	public static By clkotp5 = By.xpath("//*[@id=\"otp\"]/div/div[5]");
	public static By clkotp6 = By.xpath("//*[@id=\"otp\"]/div/div[6]");
	
	
	public static By humanicon = By.xpath("/html/body/main/div[1]/div/div[2]/div[2]/div/div[1]/div/img");
	
	public static By invalidmobileerrormsg = By.xpath("//*[@id=\"portal-root\"]/div/div[2]/div/div/div/div[1]/div/div/div/div/div[2]");
	
	//click round trip btn
	
	public static By rndTripBtn  = By.xpath("//button[text()='Round Trip']");
	public static By frmbtn = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]");
	public static By fromPlace = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input");
	
	public static By tobtn = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]");
	public static By toPlace = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input");
	public static By deptbtn = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div");
	
	
	public static By monthYear = By.xpath("//span[contains(@class,'react-calendar__navigation__label__labelText')]");

	public static By nextbtn = By.xpath("//button[contains(@class,'react-calendar__navigation__next-button')]");
	
	
	public static By adultsPlusBtn = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/button[1]");
	public static By childPlusbtn = By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/button[1]");

	//click business class and done btn
	
	public static By doneBtn = By.xpath("//button[text()='Done']");
	
	//click search btn
	
	public static By searchBtn = By.xpath("//button[text()='Search']");
	
	//validate search page is navigate or not
	public static By validateSearchPage = By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/div/div[1]/div[1]/p");
	
	public static By selectstudentoffer = By.xpath("//span[text()='Student']");
	
	
	public static By clickxgotitbtn = By.xpath("//*[@id=\"portal-root\"]/div/div[2]/div/button");
	
	public static By bookbtn = By.xpath("//button[text()='Book']");
	
	//click continue from review page
	public static By flightReviewPageContinuebtn = By.xpath("//button[contains(text(),'Continue')]");

}
