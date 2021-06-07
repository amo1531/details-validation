# details-validation

The program needs to read this data, perform some validations and then write it to a different file. The records that fail the validation, need to be put into a different file so that someone can look at them and fix the problem. Each failing record should have an additional field that will contain the reason(s) for the validation failure.

Apart from the validation, we need to add a new column called discount code to the processed records file whose value will be calculated based on the fare class field in the input record. Fare class A - E will have discount code OFFER_20, F - K will have discount code OFFER_30, L - R will have OFFER_25; rest will have no offer code.

## Input data will contain the following fields:

● First name
● Last name ● PNR
● Fare class (Single character A - Z only)
● Travel date
● Pax (no of passengers)
● Ticketing date (the date of booking)
● Email
● Mobile phone
● Booked cabin(Economy,PremiumEconomy,Business,First)

## Validations:

● Email ID is valid
● The mobile phone is valid
● Ticketing date is before travel date
● PNR is 6 characters and Is alphanumeric
● The booked cabin is valid (one of Economy, Premium Economy, Business, First)

## To run the code: 

● Provide additional input (if any) in the path - ```src/test/resources/input/correct-input.csv```
● Run the sbt test command directly

OR

● Go to the path - ```com/app/main/Main.scala and Run the code```