#include "readFile.h"
#include "randomSpace.h"
#include "algorthm2.h"
#include "algorthm1.h"
#include<iostream>
#include<string>
#include<vector>

typedef std::vector<std::pair<int,int>> pair_intv;//pair of vectors row and column
pair_intv spaces;//avalaaible ampty valid spaces in the board game
pair_intv path; //path to place your piece(s)
int rowcol_size; //mxm matrix size
bool alg1_avail_move; //true if algorthm 1 has available move, false otheriwise
bool alg2_avail_move;//true if algorthm 2 has available move, false otheriwise
char** puzzle;//mxm board game where n = rowcol_size

//constructor
game::game(){}

//initialise varables
void game::boolVariableInitialised()
{
	algorthm_1 = true;
	algorthm_2 = false;
	alg1_avail_move = true;
	alg2_avail_move = true;
}
//end of boolVariableInitialised() method

//this metho collect currently useful empty spaces in the puzzle
//collect empty valid spaces that are adjecent to pieces
void game::empty_spots(int center_spaces)
{  
	for(int row = center_spaces-2; row < center_spaces +2; row++)
		for(int col = center_spaces-2; col < center_spaces +2; col++)
		  	if(puzzle[row][col] == 'e')	  	
		  		spaces.push_back(std::make_pair(row, col));
}
//end of empty_spots method

//initialise board game which is a dynamic 
//two dimensional array
int game::init_matrix()
{
	puzzle = new char*[rowcol_size];
	
	for(int row = 0; row< rowcol_size; row++)
		puzzle[row] = new char[rowcol_size];
	
	for(int row = 0; row< rowcol_size; row++)
		for(int col = 0; col<rowcol_size; col++)
			puzzle[row][col] = 'e';

	return rowcol_size/2;
}
//end of init_matrix

//initialising center 4 spaces with white and black color
void game::centreInit(int &center_spaces)
{
	puzzle[center_spaces-1][center_spaces-1] = 'w';
	puzzle[center_spaces-1][center_spaces] = 'b'; 
	puzzle[center_spaces][center_spaces-1] = 'b';
	puzzle[center_spaces][center_spaces] = 'w';
}


//game start and other neccessary initialisation are completed here
void game::start()
{
	readFile rf;
	std::vector<int> board_sizes(rf.read_file());//get array dimensions from the file
	int size = board_sizes.size();
	
	//for each given board size play
	for(int r = 0; r < size; r++)
	{
		rowcol_size = board_sizes[r];
		std::string blue_color = "\033[1;34m";
		std::cout<<blue_color<<"************************* "<<rowcol_size<<"x"<<rowcol_size<<" puzzle *************************"<<"\n";
		int center_spaces = init_matrix();//init_matrix returns first centre cell to initialise
		centreInit(center_spaces);//initialise the 4 cneter cells
		empty_spots(center_spaces);//initialise empty spaces
		boolVariableInitialised();//initialise boolean variables
		play();//algorthm now start to play
		results();//display results
	}
}


//the actual game begin
void game::play()
{
	
	algorthm1 alg_1;
	algorthm2 alg_2;
	int count = 0;
	
	while(!spaces.empty())
	{
		
		if(!alg1MovesAvail() and !alg2MovesAvail())return;
		path.clear();
		printCurrentBoard();//print the updated board
		EmptySpacesSize();//collect nearby empty spaces
		//algorthm 1 turn
		if(algorthm_1)
		{
			std::cout<<"*******************************algorthm 1 turn********************************* \n";
			alg_1.setPosition();
			algorthm_1 = false;
			algorthm_2 = true;
		}
		else//algorthm 2 turn
		{
			std::cout<<"*******************************algorthm 2 turn********************************* \n";
			alg_2.getPosition();
			algorthm_1 = true;
			algorthm_2 = false;
		}
		std::cout<<"space size: "<<spaces.size()<<"\n";
		count++;
	}
	
	printCurrentBoard();//print the updated board
}
//end of play() method

//this method returns valid empty spaces
std::vector<std::pair<int,int>> game::getEmptyspaces()
{
	return spaces;
}

//this method returns the size empty spaces
int game::EmptySpacesSize()
{
	return spaces.size();
}


//this method returns the valid path size
int game::pathSize()
{
	return path.size();
}

//clea path
void game::clearPath()
{
	path.clear();
}

//this method removes used empty spaces
void game::removeUsedSpace(int pos)
{
	pair_intv tem;		
	newEmptySpaces(pos, tem);//add new empty space(s) temporary
	removeExistingPos(tem);//remove empty spaces that already exist to avoid duplicates
	spaces.erase(spaces.begin()+pos);//remove empty space that is now occupied
	addSpaces(tem);//add new empty space(s) permanently
}

//this method add new valid empty spaces
void game::newEmptySpaces(int pos, pair_intv &tem)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
		
	if(puzzleWall(row+1, col))
		if(puzzle[row+1][col] == 'e')//down
			tem.push_back(std::make_pair(row+1,col));
	
	if(puzzleWall(row-1, col))
		if(puzzle[row-1][col] == 'e')//up
			tem.push_back(std::make_pair(row-1,col));

	if(puzzleWall(row, col-1))
		if(puzzle[row][col-1] == 'e')//left
			tem.push_back(std::make_pair(row,col-1));
		
	if(puzzleWall(row, col+1))
		if(puzzle[row][col+1] == 'e')//right
			tem.push_back(std::make_pair(row,col+1));
		
	if(puzzleWall(row+1, col-1))
		if(puzzle[row+1][col-1] == 'e')
			tem.push_back(std::make_pair(row+1,col-1));
		
	if(puzzleWall(row-1, col-1))
		if(puzzle[row-1][col-1] == 'e')//left up diagonal
			tem.push_back(std::make_pair(row-1,col-1));
		
	if(puzzleWall(row+1, col+1))
		if(puzzle[row+1][col+1] == 'e')//right down diagonal
			tem.push_back(std::make_pair(row+1,col+1));
		
	if(puzzleWall(row-1, col+1))
		if(puzzle[row-1][col+1] == 'e')//right up diagonal
			tem.push_back(std::make_pair(row-1,col+1));
}
//end of newEmptySpaces method

//this method removes existing empty spaces in tmporary
//empty spaces to avoid duplicates
void game::removeExistingPos(pair_intv &tem)
{
	for(int r = 0; r < tem.size(); )
	{
		int size = spaces.size();
		bool removed = false;
		for(int c = 0; c< size; c++)
		{
			if(tem[r].first == spaces[c].first 
			and tem[r].second == spaces[c].second)
			{
				tem.erase(tem.begin()+r);
				removed = true;
			}
		}
		
		if(removed)r=0;
		else r++;
	}		
}
// end of removeExistingPos method


//add new valid adjacent empty spaces
void game::addSpaces(pair_intv temp)
{
	int size = temp.size();
	for(int r = 0; r < size; r++)
		spaces.push_back(temp[r]);
}

//filln the fisrt position int path
void game::fillFirstPos(int pos)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	path.push_back(std::make_pair(row,col));
}

//this method checks if temporary empty spaces exist
//in the permanent empty spaces
int game::corrPosition(std::pair<int,int> temp)
{
	int size = spaces.size();
	for(int r = 0; r < size; r++)
	{
		if(temp.first == spaces[r].first and temp.second == spaces[r].second)
			return r;
	}
	return -1;
}

//this method update algorthm 1 move
void game::alg1MovesAvail(bool move)
{
	alg1_avail_move = move;
}

//this method update algorthm 1 move
void game::alg2MovesAvail(bool move)
{
	alg2_avail_move = move;
}

//returns if algorithm 1 has a move or not
bool game::alg1MovesAvail()
{
	return alg1_avail_move;
}

//returns if algorithm 2 has a move or no
bool game::alg2MovesAvail()
{
	return alg2_avail_move;
}

//this method checks if the cell is valid
bool game::puzzleWall(int row, int col)
{
	return (row >= 0 and row < rowcol_size 
		and col >= 0 and col < rowcol_size);
}


//check down
bool game::down(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	bool inside = puzzleWall(row+1, col);
	pair_intv temp;
	
	while(inside == true)
	{
		if(puzzle[row+1][col] != piece.first)break;
		
		temp.push_back(std::make_pair(row+1, col));
		row++;
		inside = puzzleWall(row+1, col);
	}

	if(inside == true)
	{
		if(puzzle[row+1][col] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
							
			return true;
		}
	}
	
	return false;
}

//check up
bool game::up(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	pair_intv temp;
	bool inside = puzzleWall(row-1, col);
	
	while(inside == true)
	{
		if(puzzle[row-1][col] != piece.first)break;
		
		temp.push_back(std::make_pair(row-1, col));
		row--;
		
		inside = puzzleWall(row-1, col);
	}
	
	if(inside == true)
	{
		if(puzzle[row-1][col] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
					
			return true;
		}
	}
	
	return false;
}


//check left
bool game::left(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	pair_intv temp;
	bool inside = puzzleWall(row, col-1);
	
	while(inside == true )
	{
		if(puzzle[row][col-1] != piece.first)break;
		
		temp.push_back(std::make_pair(row, col-1));
		col--;
		inside = puzzleWall(row, col-1);
	}
	
	if(inside == true)
	{
		if(puzzle[row][col-1] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
						
			return true;
		}
	}
	
	return false;
}


//check right
bool game::right(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	pair_intv temp;	
	bool inside = puzzleWall(row, col+1);
	
	while(inside == true)
	{
		if(puzzle[row][col+1] != piece.first)break;
		
		temp.push_back(std::make_pair(row, col+1));
		col++;
		inside = puzzleWall(row, col+1);
	}
	;
	if(inside == true)
	{
		if(puzzle[row][col+1] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
					
			return true;
		}
	}
		
	return false;
}


//chec left down diagonal
bool game::l_downDiagonal(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	pair_intv temp;	
	bool inside = puzzleWall(row+1, col-1);
	
	while(inside == true)
	{
		if(puzzle[row+1][col-1] != piece.first)break;
		
		temp.push_back(std::make_pair(row+1, col-1));
		col--;
		row++;
		inside = puzzleWall(row+1, col-1);
	}
	
	if(inside == true)
	{
		if(puzzle[row+1][col-1] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
								
			return true;
		}
	}
		
	return false;
}


//check left up diagonal
bool game::l_upDiagonal(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	pair_intv temp;
	bool inside = puzzleWall(row-1, col-1);
	
	while(inside == true)
	{
		if(puzzle[row-1][col-1] != piece.first)break;
		
		temp.push_back(std::make_pair(row-1, col-1));
		col--;
		row--;
		inside = puzzleWall(row-1, col-1);
	}
	
	if(inside == true)
	{
		if(puzzle[row-1][col-1] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
				
			return true;
		}
	}
	return false;
}


//check right down diagonal
bool game::r_downDiagonal(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	pair_intv temp;
	bool inside = puzzleWall(row+1, col+1);
	
	while(inside == true)
	{
		if(puzzle[row+1][col+1] != piece.first)break;
		
		temp.push_back(std::make_pair(row+1, col+1));
		col++;
		row++;
		inside = puzzleWall(row+1, col+1);
	}
	
	if(inside == true)
	{
		if( puzzle[row+1][col+1] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
				
			return true;
		}
	}
	
	return false;
}


//check right up diagonal
bool game::r_upDiagonal(int pos, pair_char piece)
{
	int row = spaces[pos].first;
	int col = spaces[pos].second;
	pair_intv temp;
	bool inside = puzzleWall(row-1, col+1);
	
	while(inside == true)
	{
		if(puzzle[row-1][col+1] != piece.first)break;
		temp.push_back(std::make_pair(row-1, col+1));
		col++;
		row--;
		inside = puzzleWall(row-1, col+1);
	}
	
	if(inside == true)
	{
		if(puzzle[row-1][col+1] == piece.second and !temp.empty())
		{
			int temp_size = temp.size();
			for(int r = 0; r < temp_size; r++)
				path.push_back(temp[r]);
					
			return true;
		}
	}
	
	return false;
}

//fill the board using the longest path
void game::ownPath(char piece)
{
	int size = path.size();
	for(int r = 0; r < size; r++)
		puzzle[path[r].first][path[r].second] = piece;
}

//print result
void game::results()
{
	int alg1_count = 0;
	int alg2_count = 0;
	
	for(int r = 0; r < rowcol_size; r++)
	{
		for(int c = 0; c < rowcol_size; c++)
		{
			if(puzzle[r][c] == 'b')
				alg1_count++;
			else if(puzzle[r][c] == 'w')
				alg2_count++;
		}
	}
	
	
	std::cout<<"Algorthm 1 score: "<<alg1_count<<"\n";
	std::cout<<"Algorthm 2 score: "<<alg2_count<<"\n";
	
	if(alg1_count > alg2_count)
		std::cout<<"Algorthm 1 won 🤸🤸🤸🤸🤸\n";
	else if(alg1_count < alg2_count)
		std::cout<<"Algorthm 2 won 🤸🤸🤸🤸🤸\n";
	else
		std::cout<<"Draw 🤼👊\n";
}


void game::printCurrentBoard()
{
	int row;
	int col;
	for(row = 0; row< rowcol_size; row++)
	{
		for(col = 0; col<rowcol_size; col++)
			std::cout<<" "<<puzzle[row][col];
		std::cout<<"\n";		
	}
}
//Tholithemba
