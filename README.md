# BundleCalculator

## Requirements:

Social media Influencers have been basing the price of their social media post on a single post basis. So If a brand required 10 posts (for example spread over a period) then they would be charged 10x the cost of a single post. The company has decided to allow social media influencers to sell posts in bundles and charge the brand on a per bundle basis. So if the Influencer sold image based posts in bundles of 5 and 10 and brand ordered 15 they would get a bundle of 10 and a bundle of 5.

## Usage:

1. Create a txt file on your disk and input the bundle cost plan
![Alt text](/designDoc/screenShot/1.png)  

2. Create a txt file on your disk and input the order data
![Alt text](/designDoc/screenShot/2.png)  

3. Run Java Application "BundleCalculator"
![Alt text](/designDoc/screenShot/3.png)  

4. Input command "upload <XXX\XXX.txt>" and press "Enter". <XXX\XXX.txt> is the bundle cost file path and file name you have created in step 1
![Alt text](/designDoc/screenShot/4.png)  

5. Input command "cal <XXX\XXX.txt>" and press "Enter". <XXX\XXX.txt> is the order data file path and file name you have created in step 2
![Alt text](/designDoc/screenShot/5.png)  

6. You can find the calculation result in console
![Alt text](/designDoc/screenShot/6.png)

## Class Diagram
![Alt text](/designDoc/BundleCalculator_ClassDiagram.jpg)

## Project folder structure:
Root  
|--	src  
|		|-- exception  ->Package for exception classes used in project  
|		|-- main  ->Package for main logic implementation classes  
|		|-- main.interface  ->Package for defined interfaces  
|		|-- test  ->Package for Unit Test classes  
|--	designDoc  ->Package for design documents such as class diagram
