#!/usr/bin/env python

import QSTK.qstkutil.qsdateutil as du
import QSTK.qstkutil.tsutil as tsu
import QSTK.qstkutil.DataAccess as da

import datetime as dt
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np


def assess(d_data, dt_start, dt_end, symbols, allocations):

	na_prices = d_data['close'].values

	# normalize prices
	na_daily_returns = na_prices / na_prices[ 0, : ]
	# get the portfolio value for each day by muliplying the normalized stocks'
	# total return by that day by it's portfolio weight and summing
	na_portfolio_value = np.sum(na_daily_returns * allocations, axis=1)
	# get daily returns ((T.i - T.i-1)/(T.i-1))
	na_portfolio_returns = na_portfolio_value.copy()
	tsu.returnize0(na_portfolio_returns)

	std_daily_returns = np.std(na_portfolio_returns)
	avg_daily_returns = np.average(na_portfolio_returns)
	# cumulative returns is the last value of the portfolio value
	cum_returns = na_portfolio_value[len(na_portfolio_value) - 1] 
	k = np.sqrt(252)
	sharpe = k * (avg_daily_returns / std_daily_returns)
	return std_daily_returns, avg_daily_returns, sharpe, cum_returns

def main():

	# choose the date range and equities to analyze
	dt_start = dt.datetime(2010,1,1)
	dt_end = dt.datetime(2010,12,31)
	symbols = ['BRCM', 'TXN', 'AMD', 'ADI']

	dt_timeofday = dt.timedelta(hours=16)
	ldt_timestamps = du.getNYSEdays(dt_start, dt_end, dt_timeofday)
	ls_keys = ['close']
	c_dataobj = da.DataAccess('Yahoo')
	ldf_data = c_dataobj.get_data(ldt_timestamps, symbols, ls_keys)
	d_data = dict(zip(ls_keys, ldf_data))

	best_allocation = []
	best_numbers = [0.0, 0.0, 0.0, 0.0]

	for alloc_0 in range(0,11):
		for alloc_1 in range(0,11):
			for alloc_2 in range(0,11):
				for alloc_3 in range(0,11):
					if alloc_0 + alloc_1 + alloc_2 + alloc_3 == 10:
						allocations = [alloc_0/float(10), alloc_1/float(10), alloc_2/float(10), alloc_3/float(10)]
						std, avg, sharpe, cumulative = assess(d_data, dt_start, dt_end, symbols, allocations)
						if sharpe > best_numbers[2]:
							best_allocation = allocations 		
							best_numbers = [std, avg, sharpe, cumulative]


	print "Start Date: ", dt_start.strftime("%B %d, %Y")
	print "End Date: ", dt_end.strftime("%B %d, %Y")
	print "Symbols: ", str(symbols) 
	print "Optimal Allocations: ", str(best_allocation)
	print "Sharpe Ratio: ", best_numbers[2]
	print "Volatility: ", best_numbers[0] 
	print "Average Daily Return: ", best_numbers[1]
	print "Cumulative Returns ", best_numbers[3] 



if __name__ == '__main__':
    main()
