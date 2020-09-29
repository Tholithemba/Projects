#ifndef SPARESTRIKE_H
#define SPARESTRIKE_H
#include<string>
//Tholithemba Mngomezulu
//definitions
class sparestrike
{
	public:
		sparestrike();
		void set_score(const int arr[], int size);
		void update_current_frames(int* &frame, int& frame_number);
		void last_frame(int* &frame, int& frame_number);
		std::string* spare_strikeFrame;
		
	private:
		bool points_added(int* &frame, int& frame_number);
		bool strikeprev_frame(int frame_number);
		void all_frames(int* &frame, int& frame_number);
		bool spareprev_frame(int frame_number);
		void prevFrameSpareStrike(int prevframe_number, bool* &str_sp);
		void add_points(int* &frame, int frame_number);
		void update_Frames_and_Values(int* &frame, int &frame_number);
		void initilisVariables();
		bool twiceRoll_inFrame();
		void update_roll();
		bool check_roll();
		void next_value();
		void decreement_roll();
		int get_score();
		int pins_hitted[22];
		static const int MAX = 10;
		const std::string sp = "spare";
		const std::string str = "strike";
		int score;
		bool twice;
		int roll;
		int next;
				
};

#include "sparestrike.cpp"
#endif
