#include "sparestrike.h"

//constructor
sparestrike::sparestrike(){}

void sparestrike::initilisVariables()
{
	spare_strikeFrame = new std::string[MAX];
	for(int r = 0; r < MAX; r++)
		spare_strikeFrame[r] = "null";

	roll = 1;//two chances to roll
	next = 0;//move to next pin hitted
}
//setting score value
void sparestrike::set_score(const int arr[], int size)
{
	initilisVariables();
	for(int t = 0; t < size; t++ )
		pins_hitted[t] = arr[t];
}

//getting number of pins hitted by the player in the arraay
int sparestrike::get_score()
{
	return pins_hitted[next];
}


//checking if all pins are hitted or some are hitted 
void sparestrike::update_current_frames(int* &frame, int& frame_number)
{
	score = get_score();//get value of pins hitted
	next_value();//move to next pin hitted

	if(score < MAX)
	{   //update prevois and current frame
		update_Frames_and_Values(frame, frame_number); 
		
		decreement_roll();//decrement number of rolls
		//update spare_strikeFrame frame by spare if frame score is equal MAX
		if(frame[frame_number] == MAX)
			spare_strikeFrame[frame_number] = sp;
	}
	else
	{
		twice = false;
		all_frames(frame, frame_number);
		//update spare_strikeFrame frame by strike if frame
		// score is equal MAX after second roll
		spare_strikeFrame[frame_number] = str;
	}
}

//checking frames and update if neccessary
void sparestrike::all_frames(int* &frame, int& frame_number)
{
		add_points(frame, frame_number);//add number pins hitted int frame
		
		if(frame_number == 0 )return;
		
		bool *str_sp;
		str_sp = new bool[2];
		//check if previous frame is a frame or strike
		prevFrameSpareStrike(frame_number, str_sp);
		
		if(str_sp[0] == true or (str_sp[1] == true and check_roll() != true))
		{   //add points to previous if it is spare or strike
			add_points(frame, (frame_number - 1));
			
			if(frame_number == 1 or twiceRoll_inFrame()) return;
			//check if prev -1 frame is strike
			bool strikeprev = strikeprev_frame((frame_number - 1));
			//add point if previos previous frame is a strike
			if(strikeprev == true and frame_number > 1)
					add_points(frame, (frame_number - 2));
		}
}


//last frame as the update is totaly different from other frames
void sparestrike::last_frame(int* &frame, int &frame_number)
{
		score = get_score();//get pins hitted
		next_value();//move to next pin hitted
		
		if(score < 10)
		{
			update_Frames_and_Values(frame, frame_number);//update score in the frame
			//if total score in the frame is eqal to MAX label frame as spare
			if(frame[frame_number] == MAX)
				spare_strikeFrame[frame_number] = sp;

		}
		else if(score == MAX)
		{
			twice = false;
			///if 10 pins hitted label frame as strike
			spare_strikeFrame[frame_number] = str;			
			update_Frames_and_Values(frame, frame_number);//update score in the frame
			
			//check two previous frames and add points if found to spare or strike
			if(spare_strikeFrame[frame_number - 1] == str)
				add_points(frame, (frame_number - 1));		
		}
		
		frame[frame_number] += get_score();
}

//update of values 
void sparestrike::update_Frames_and_Values(int* &frame, int &frame_number)
{
		all_frames(frame, frame_number);//update frames
		update_roll();//update roll
		score = get_score(); //get pins hitted
		next_value();//move to next pin hitted
		twice = true;//previous previous frame need to be updated
		all_frames(frame, frame_number);//update frames
}

//returns boolean array which tells us whether the two previous frames were spare or strike 
void sparestrike::prevFrameSpareStrike(int prevframe_number, bool* &str_sp)
{	
		bool strikeprev, spareprev;
		
		strikeprev = strikeprev_frame(prevframe_number); //checking if previous frame was a strike
		spareprev = spareprev_frame( prevframe_number);  //checking if previous frame was a spare
		
		str_sp[0] = strikeprev;
		str_sp[1] = spareprev;				
}

//next number of pins in the array hitted by the player
void sparestrike::next_value()
{
		next++;//move to next pin hitted
}	

//increment roll
void sparestrike::update_roll()
{
		roll++;
}

//decrement roll back to default roll;
void sparestrike::decreement_roll()
{
		roll--;
}

//points added to current frame
void sparestrike::add_points(int* &frame, int curr_frame_number)
{
		frame[curr_frame_number] += score;		
}
//end of add_points() method

//checking if it is the second roll
bool sparestrike::check_roll()
{
		if(roll == 2)
			return true;
		
		return false;
}

//checking if the prev prev frame is neccessary to be updated
bool sparestrike::twiceRoll_inFrame()
{
	return twice;
}

//checking if previous frame was a strike
bool sparestrike::strikeprev_frame(int strframe_number)
{
		return spare_strikeFrame[strframe_number - 1] == str;
}

//checking if previous frame was a spare
bool sparestrike::spareprev_frame(int spframe_number)
{
		return spare_strikeFrame[spframe_number - 1] == sp;
}

