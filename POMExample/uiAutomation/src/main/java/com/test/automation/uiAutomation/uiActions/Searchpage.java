package com.test.automation.uiAutomation.uiActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.test.automation.uiAutomation.homepage.explicitWait;

public class Searchpage {

	WebDriver driver;
	explicitWait wait;

	public Searchpage(WebDriver driver) {
		this.driver = driver;
		wait = new explicitWait(driver);
	}

	// Search Box
	@FindBy(id = "edit-apachesolr-panels-search-form")
	public WebElement searchBox;

	// Search Icon
	@FindBy(id = "edit-submit")
	public WebElement searchIcon;

	// Filter By Format Article
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-1']/input")
	public WebElement articleFilter;

	// Filter By Format Q&A Forum
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-2']/input")
	public WebElement QAFilter;

	// Filter By Format Research
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-3']/input")
	public WebElement researchFilter;

	// Filter By Format Interview
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-4']/input")
	public WebElement interviewFilter;

	// Filter By Format Video
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-5']/input")
	public WebElement videoFilter;

	// Filter By Format Event
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-6']/input")
	public WebElement eventFilter;

	// Filter By Format Slideshare
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-7']/input")
	public WebElement slideshareFilter;

	// Filter By Format Interactive
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-bundle']/li[@class='leaf list-8']/input")
	public WebElement interactiveFilter;

	// Filter By Date 2013
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-created']/li[@class='leaf list-1']/input")
	public WebElement date2013;

	// Filter By Date 2013
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-created']/li[@class='leaf list-2']/input")
	public WebElement date2014;

	// Filter By Date 2013
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-created']/li[@class='leaf list-3']/input")
	public WebElement date2015;

	// Filter By Date 2013
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-created']/li[@class='leaf list-4']/input")
	public WebElement date2016;

	// Filter By Date 2013
	@FindBy(xpath = "//ul[@id='facetapi-facet-apachesolrsolr-block-created']/li[@class='leaf list-5']/input")
	public WebElement date2017;

	// Search Result 1st result
	@FindBy(xpath = "//section[@class='search-results']/article[1]//h3//a")
	public WebElement firstSearchResults;

	// Main title Search Result Detail page
	@FindBy(xpath = "//h1[@class='field-content']")
	public WebElement titleDetailPage;

	// Ask the community link on Search page
	@FindBy(xpath = "//div[@class='q-a-link_SRP']/a")
	public WebElement askCommunityLink;

	// Ask the Community Page heading
	@FindBy(xpath = "//h1")
	public WebElement askCommunityHeading;

	// No Search Results Title
	@FindBy(xpath = "//div[@class='panel-pane pane-apachesolr-result']//h2")
	public WebElement noResultTitle;

}
