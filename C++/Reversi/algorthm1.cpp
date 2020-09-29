#include "algorthm1.h"
#include "game.h"

//constructor
algorthm1::algorthm1(){}

//this method set positon and che the valid path from that position
void algorthm1::setPosition()
{
	int size = g.EmptySpacesSize();//get valid empty spaces
	max_size = 0;
	long_path_pos = 0;
	//the algorthm look for the longest valid path in all direction from a given valid cell
	for(int r = 0; r < size; r++)
		findPath(r);

	if(!path2fill.empty())
	{
		longPath();//long path
		g.ownPath('b');//algorthm 1 writes its path in the puzzle
		g.alg1MovesAvail(true);// algorthm 1 still have available move(s)
		path2fill.clear();//clear the shortest path and update with the longest path
		return;
	}
	
	g.alg1MovesAvail(false);//algorthm 1 no onger have available move(s)
}
//end of setPosition() method


//algerthm 1 look for the longest path in all directions from the current cell
void algorthm1::findPath(int pos)
{
	std::pair<char,char> pieces;
	pieces.first = 'w';
	pieces.second = 'b';
	ch_vec temp;
	
	if(g.down(pos, pieces))
		temp.push_back('1');

	if(g.up(pos, pieces))
		temp.push_back('2');
	
	if(g.left(pos, pieces))
		temp.push_back('3');
	
	if(g.right(pos, pieces))
		temp.push_back('4');
	
	if(g.l_downDiagonal(pos, pieces))
		temp.push_back('5');
		
	if(g.l_upDiagonal(pos, pieces))
		temp.push_back('6');
	
	if(g.r_downDiagonal(pos, pieces))
		temp.push_back('7');
	
	if(g.r_upDiagonal(pos, pieces))
		temp.push_back('8');
		
	if(!temp.empty())// if the temp vector is different from zero compare path to find the longest path
		pathOfBigLength(temp, pos);
}
//end of findPath method


//this method compare the temporary filled path with the longest path. if new temp path is longer than the
//current origina path update original path
void algorthm1::pathOfBigLength(ch_vec temp, int pos)
{
	int path_length = g.pathSize();

	if(path_length > max_size)
	{
		int temp_size = temp.size();
		path2fill.clear();
		for(int r = 0; r < temp_size; r++)
			path2fill.push_back(temp[r]);

		long_path_pos = pos;
		max_size = path_length;
	}
	g.clearPath();
}
//end of pathOfBigLength method


//final update the longest path and inert it in the puzzle
void algorthm1::longPath()
{
	g.clearPath();
	std::pair<char,char> pieces;
	pieces.first = 'w';
	pieces.second = 'b';
	int path2f_length = path2fill.size();
	g.fillFirstPos(long_path_pos);

	for(int r = 0; r < path2f_length; r++)
	{
		switch(path2fill[r])
		{
			case '1': g.down(long_path_pos, pieces);
			break;
			
			case '2': g.up(long_path_pos, pieces);
			break;
			
			case '3': g.left(long_path_pos, pieces);
			break;
			
			case '4': g.right(long_path_pos, pieces);
			break;
			
			case '5': g.l_downDiagonal(long_path_pos, pieces);
			break;
				
			case '6': g.l_upDiagonal(long_path_pos, pieces);
			break;
			
			case '7': g.r_downDiagonal(long_path_pos, pieces);
			break;
			
			case '8': g.r_upDiagonal(long_path_pos, pieces);
			break;
			
			default: std::cout<<"error occurred!!!!! 🚫\n";
		}
	}
	
	g.removeUsedSpace(long_path_pos);//remove empty space that is already filled
}
