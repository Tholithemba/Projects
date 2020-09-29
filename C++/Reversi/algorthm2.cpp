#include "algorthm2.h"
#include "game.h"
#include<vector>
#include<iostream>
game g;


//constructor
algorthm2::algorthm2(){}	


//this metho check valind empty position
//and find the valid path by chosing random psition in empty spcace vector
void algorthm2::getPosition()
{
	randomSpace rs;
	int pos = 0;
	
	if(g.EmptySpacesSize() != 0)
	{
		pair_intv temp(g.getEmptyspaces());//copy empty spaces to new temp vector
		//we loop until we find the random position that is going to give us the valid path
		//else we hand over to algorthm 1
		while(!temp.empty())
		{
			if(g.pathSize() != 0)return;
			
			int newPos;
			std::pair<int,int> temp_pair;
			
			pos = rs.getRandPosition(temp.size());//get random psotion from temp vector
			temp_pair.first = temp[pos].first;//row
			temp_pair.second = temp[pos].second;//column
			temp.erase(temp.begin() + pos);//remove chosen cell from temp vector
			newPos = g.corrPosition(temp_pair);//new postion is assigned
			findpath(newPos);//find the path using give position
		}
		
		g.alg2MovesAvail(false);//algorhtom 2 nolonger have available moves
	}
}

//look for path in all directions in the current cell	
void algorthm2::findpath(int pos)
{	
	std::pair<char,char> pieces;
	pieces.first = 'b';
	pieces.second = 'w';
	
	g.down(pos, pieces);
	
	g.up(pos, pieces);
	
	g.left(pos, pieces);
	
	g.right(pos, pieces);
	
	g.l_downDiagonal(pos, pieces);
		
	g.l_upDiagonal(pos, pieces);
	
	g.r_downDiagonal(pos, pieces);
	
	g.r_upDiagonal(pos, pieces);
		
	if(g.pathSize() != 0)
	{
		g.fillFirstPos(pos);//fill the first position int path
		g.ownPath(pieces.second);//fill the valid path in the board or puzzle
		g.removeUsedSpace(pos);//remove used position
		g.alg2MovesAvail(true);//available moves to algorthm 2 still available
	}
}		
