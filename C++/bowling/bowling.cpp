#include "sparestrike.h"
#include "testcase.h"
#include "bowling.h"
#include<iostream>
#include<string>
#include<fstream>
#include<iomanip>
//Tholithemba
sparestrike ss;

bowling::bowling(){}//constructor

//initialising frame 
void bowling::initialising_arrays()
{
	for(int i = 0; i < 10; i++)
		frame[i] = 0;
}
//end of initialising frame

//start list method
void bowling::list()
{
	frame = new int[10];
	
	const int arr1[17] = {10,7,3,7,2,9,1,10,10,10,2,3,6,4,7,3,3}; // array of size 17
	const int arr2[17] = {10,9,1,5,5,7,2,10,10,10,9,0,8,2,9,1,10}; // array of size 17
	const int arr3[19] = {8,2,5,4,9,0,10,10,5,5,5,3,6,3,9,1,9,1,10};// array of size 19
	int sizeof_arr1 = 17; // size of the first array
	int sizeof_arr2 = 17; // size of the second array
	int sizeof_arr3 = 19; // size of the theird array

	for(int r = 0; r < 3; r++)
	{
		initialising_arrays();//initialising frame to contain zeros
		
		if(r == 0)
		{
			ss.set_score(arr1, sizeof_arr1); //first array and size are passed to set the pins hitted
			fillThFrame(); // start filling the frame
		}
		else if(r == 1)
		{
			ss.set_score(arr2, sizeof_arr2);//second array and size are passed to set the pins hitted
			fillThFrame();// start filling the frame
		}
		else
		{
			ss.set_score(arr3, sizeof_arr3);//third array and size are passed to set the pins hitted
			fillThFrame();// start filling the frame
		}
	}
}//end of list method


//fillinf the frame using pins hitted by the player
void bowling::fillThFrame()
{
	
	int frame_number = 0;
	
	//update frame if it frame number still less 9
	while(frame_number < 9)
	{
		ss.update_current_frames(frame, frame_number);//update current frame
		frame_number++;//go to next frame
	}

	ss.last_frame(frame, frame_number); //update last frame seperately

	print_Results();//print results

	runn_testcases();//test if actual results correspong to expected results
}
//end of fillThFrame() method

//print method
void bowling::print_Results()
{
	printFrameNumber();//print frame number
	printFrameScore(); //print frame score
	printRunningTotals(); //print accumulated total
	printSpareStrikeFrame();//print to see whether the frame block contains spare or strike
}
//end of pring method

//print Frame number method
void bowling::printFrameNumber()
{
	std::string green_color = "\033[1;32m";
	std::cout<<green_color<<"Frame number:     ";
	for(int f = 1; f <= 10; f++)
		std::cout<<f<<std::right<<std::setw(8);
	std::cout<<"\n";
}
//end of print Frame number method

//print frame score method
void bowling::printFrameScore()
{
	std::cout<<"Frame score:     ";
	for(int f = 0; f < 10; f++)
		std::cout<<frame[f]<<std::setw(8);
	std::cout<<"\n";
}
//end of print frame score method

//print running totals method
void bowling::printRunningTotals()
{
	int sum = 0;
	std::cout<<"Running totals:  ";
	for(int f = 0; f < 10; f++)
	{
		sum += frame[f];
		std::cout<<sum<<std::setw(8);
	}
	std::cout<<"\n";
}
//end of printing running totals method


// print Spare Strike Frame method
//the frame is may be null or a spare or a strike
void bowling::printSpareStrikeFrame()
{
	std::cout<<"Strike/Spare:  ";
	for(int f = 0; f < 10; f++)
		std::cout<<ss.spare_strikeFrame[f]<<" ";
	std::cout<<"\n";
}
//end of print Spare Strike Frame method


//test case method
void bowling::runn_testcases()
{
	testcase tc;
	std::string test_case_result;
	int sum = 0;
	
	//total score
	for(int f = 0; f < 10; f++)
		sum += frame[f];
		
	std::cout<<"Test cases"<<"\n";	
	//return passed if total score correspond to actaul total otherwise return fail
	//test case 1
	test_case_result = tc.runing_totalstest(sum);
	std::cout<<test_case_result<<"\n";//print resulthttps://github.com/Tholithemba/Projects.git
	
	//return passed if spare_strike Frame correspond to actaul spare_strike frame otherwise return fail
	//test case 2	
	test_case_result = tc.sp_strArraytest(ss.spare_strikeFrame);
	std::cout<<test_case_result<<"\n";//print result
	std::cout<<"\n";
}
