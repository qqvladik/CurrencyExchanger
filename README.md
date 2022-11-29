# Currency Exchanger

## Tech stack

- Coroutines
- Dagger 2
- MVVM, Clean Architecture
- Room
- Retrofit
- Kotlin

## Description

The application can work with the network and without. Request to the network occurs every 5 seconds.

When you turn on the application without a network, the application displays a Toast message about the absence of a network.

The user has a multi-currency account with an initial balance of 1000 euros (EUR). It can convert any currency to any as long as the rate is provided by the API, but the balance cannot fall below zero.

When you enter the amount of the sold currency, the amount of the received currency is immediately calculated. An empty input field means 0.

There is a handling of various situations and a dialog box output:
1. Error. Missing balance with the specified currency type
2. Error. Currency types not loaded
3. Error. The same type of currency sold and received is selected
4. Error. The indicated amount (including commission) is more than what is on the balance sheet
5. Success. The amount and type of currency sold and received are indicated. There is also a commission.

The first five currency exchanges are free of charge but afterwards they're charged commission 0.7% of the currency being traded.

Time spent on the task - 20 hours.
git repository - https://github.com/qqvladik/CurrencyExchanger.git
