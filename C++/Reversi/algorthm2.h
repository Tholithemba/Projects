#ifndef ALGORTHM2_H
#define ALGORTHM2_H

class algorthm2
{
	public:
		algorthm2();
		void getPosition();
		
	private:
		void findpath(int pos);
		typedef std::vector<std::pair<int,int>> pair_intv;
		pair_intv spaces;
};
#include "algorthm2.cpp"
#endif
