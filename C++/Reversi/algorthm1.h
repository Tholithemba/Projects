#ifndef ALGORTHM1
#define ALGORTHM1
#include<vector>
#include<string>

class algorthm1
{
	public:
		algorthm1();
		void setPosition();
	
	private:
		void findPath(int pos);
		typedef std::vector<std::string> str_vec;
		typedef std::vector<std::pair<int,int>> pair_intv;
		typedef std::vector<char> ch_vec;
		void pathOfBigLength(ch_vec temp, int pos);
		void longPath();
		std::vector<char> path2fill;
		pair_intv long_path;
		void saveLongPath();
		void updateLongPath();
		int max_size;
		int long_path_pos;
};

#include "algorthm1.cpp"
#endif
