# How to run
Open any IDE. Right click "RewardApplication" and select run

# Endpoint
There two endpoint.
* Get /customer/reward/view/points?customerId={customerId}  <br /> 
Get customer reward by month
* Post /customer/reward/process/transaction <br /> 
Post list of transaction for this customer by month in order to calculate reward point.
Sample json file is in file "sample.json"