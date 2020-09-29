#include "topten.h"
#include "random_access.h"
#include<iostream>
#include<bits/stdc++.h>

//constructor
topten::topten()
{
	player_1 = true;
	player_2 = false;
	repeat = false;
	first_time = true;
	//initialising cards list
	for(int r = 1; r <= 10; r++)
		for(int c = 0; c < 4; c++)
			cardlist.push_back(std::make_pair(r, card_type[c]));
	
	card_stack = cardlist.size();//total number of cards
}


//start the game
void topten::start()
{
	while(card_stack  > 0)
	{
		std::cout<<"card left: "<<card_stack<<std::endl;
		card_position();//card position
		next_player();//next player turn
		card_stack = cardlist.size();
	}
	results();//print results
}


//get the card in a given postion
void topten::card_position()
{
	int number_of_cards = card_stack;
	random_access ra;

	int card_pos = ra.random_position(number_of_cards);//random postion

	card opened_card = cardInfo(card_pos);//card in selected position
	std::cout<<"card opened: "<<opened_card.first<<" "<<opened_card.second<<std::endl;
	summation(opened_card);//check if the card can be combined with other cards to kame total of 10
}

void topten::summation(card opened_card)
{
	cards temp;
	int sum = 0;
	
	firstTime();
	bool first_condition = firstCondition(opened_card.first);
	//if player open the for the first time
	if(first_condition)
	{
		open_table.push_back(opened_card);//add cards that didn't add up to ten to open table
		repeat = false;
	}
	else
	{//if open card is different from 10
		if(opened_card.first != MaxSum)
		{
			sum = doubleCheckSum(opened_card);
		}
		else if(opened_card.first == MaxSum)
		{
			sum = opened_card.first;
			
			if(player_1)
			{
				player_1Cards.push_back(opened_card);
			}
			else
			{
				player_2Cards.push_back(opened_card);
			}
		}	
		//check if the player repeat	
		repeatOrNot(sum, opened_card);
	}
}


//check if the player open the first card
bool topten::firstCondition(int opened_card)
{
	int open_table_size = open_table.size();
	return first_time == true and open_table_size == 0 and opened_card != MaxSum;
}


//this method add the opponent card in the table list
void topten::firstTime()
{
	card temp_pair;
	int stack_size;

	if(!first_time)
	{
		if(player_1)
		{
			stack_size = player_1Cards.size();
			if(stack_size != 0)
			{
				temp_pair = player_1Cards[stack_size - 1];
				open_table.push_back(temp_pair);
			}
		}
		else
		{
			stack_size = player_2Cards.size();
			if(stack_size != 0)
			{
				temp_pair = player_2Cards[stack_size - 1];
				open_table.push_back(temp_pair);
			}
		}
	}
}

//this method checks if the player has to repeat or not
void topten::repeatOrNot(int sum, card opened_card)
{
	
	if(sum == MaxSum)
	{
		repeat = true;
	}
	else
	{
		open_table.push_back(opened_card);
		repeat = false;
	}
}

//find sum using opened card and cards that are on the open table
int topten::minOf1_sumation(card opened_card)
{
	cards temp;
	int sum=0;
	int size = open_table.size();
	
	for(int r = 0; r < size; r++)
	{
		sum = opened_card.first + open_table[r].first;
		if(sum == MaxSum)
		{
			temp.push_back(open_table[r]);
			temp.push_back(opened_card);
			//if sum is equl to 10 add to players stack
			addToPlayers_stack(temp);
			return sum;
		}
	}
	
	return sum;
}


//sum of opened card plus two cards on the open table
int topten::minOf2_sumation(card opened_card)
{
	cards temp;
	int sum = 0;
	int size = open_table.size();
	
	for(int r = 0; r < size - 1; r++)
	{
		for(int c = r + 1; c < size; c++)
		{
			sum = opened_card.first + open_table[r].first + open_table[c].first;

			if(sum == MaxSum)
			{
				temp.push_back(open_table[r]);
				temp.push_back(open_table[c]);
				temp.push_back(opened_card);
				//if sum is equl to 10 add to players stack
				addToPlayers_stack(temp);
				return sum;
			}
		}
	}
	return sum;
}

//sum of opened card plus three cards on the open table
int topten::minOf3_sumation(card opened_card)
{
	cards temp;
	int sum = 0;
	int size = open_table.size();

	for(int r = 0; r < size - 2; r++)
	{
		for(int c = r + 1; c < size - 1; c++)
		{
			for(int d = c + 1; d < size; d++)
			{
				sum = opened_card.first + open_table[r].first + open_table[c].first + open_table[d].first;
				if(sum == MaxSum)
				{
					temp.push_back(open_table[r]);
					temp.push_back(open_table[c]);
					temp.push_back(open_table[d]);
					temp.push_back(opened_card);
					//if sum is equl to 10 add to players stack
					addToPlayers_stack(temp);
					return sum;
				}
			}
		}
	}
	return sum;	
}

//sum of opened card plus four cards on the open table
int topten::minOf4_sumation(card opened_card)
{
	cards temp;
	int sum = 0;
	int size = open_table.size();
	
	for(int r = 0; r < size - 3; r++)
	{
		for(int c = r + 1; c < size - 2; c++)
		{
			for(int d = c + 1; d < size - 1; d++)
			{
				for(int e = d + 1; e < size; e++)
				{
					sum = opened_card.first + open_table[r].first + open_table[c].first + open_table[d].first + open_table[e].first;

					if(sum == MaxSum)
					{
						temp.push_back(open_table[r]);
						temp.push_back(open_table[c]);
						temp.push_back(open_table[d]);
						temp.push_back(open_table[e]);
						temp.push_back(opened_card);
						//if sum is equl to 10 add to players stack
						addToPlayers_stack(temp);
						return sum;
					}
				}
			}
		}
	}
	return sum;//return sum
}


//check if cards on the open table plus the opponent card that is
//on top of the stack if they sumup to ten and add to stack
//if it is the case
int topten::doubleCheckSum(card opened_card)
{
	int sum = 0;
	int open_table_size = open_table.size();
	
	sum = minOf1_sumation(opened_card);
	if(sum != MaxSum and open_table_size > 1)
	{
		sum = minOf2_sumation(opened_card);
		
		if(sum != MaxSum and open_table_size > 2)
		{
			sum = minOf3_sumation(opened_card);
			
			if(sum != MaxSum and open_table_size > 3)
				sum = minOf4_sumation(opened_card);
		}
	}
	return sum;
}

//add card(s) to players stack
void topten::addToPlayers_stack(cards temp)
{
	std::sort(temp.begin(), temp.end());
	std::reverse(temp.begin(), temp.end());
	
	updatingTableSize(temp);	
	
	if(player_1)
	{ //update players stack
		updatePlayerCards(player_1Cards, temp);
		//sum of cards that are in open table plus th one that is on top
		//of opponent stack. add to the players stack if sum is equla to 10
		findOtherSum(player_2Cards, temp);
	}
	else
	{  ///update players stack
		updatePlayerCards(player_2Cards, temp);
		//sum of cards that are in open table plus th one that is on top
		//of opponent stack. add to the players stack if sum is equla to 10
		findOtherSum(player_1Cards, temp);
	}
}


//update player stack
void topten::updatePlayerCards(cards &pl_cards, cards temp)
{
	int temp_size = temp.size();
	
	for(int r = 0; r < temp_size; r++)
			pl_cards.push_back(temp[r]);	
}


//sum of cards that are in open table plus th one that is on top
//of opponent stack. add to the players stack if sum is equla to 10
void topten::findOtherSum(cards &pl_cards, cards temp)
{	
	int temp_size = temp.size();
	int open_table_size = open_table.size();//cards that are in the open table
	int pl_cards_size = pl_cards.size(); //current player stack size
	
	for(int r = 0; r < temp_size; r++)
	{
		if(pl_cards_size != 0)
		{
			if(temp[r] == pl_cards[pl_cards_size -1] )
			{
				pl_cards.pop_back();
				//if there cards in open table. check all cards 
				//in the open table plus opponent top card on the stack 
				//if sum is equal to 10 add to players stack
				if(open_table_size != 0)
					doubleCheckSum(pl_cards[pl_cards_size -1]);
			}
		}
	}
}

//update open table size
//cards that when you sum them. they don't add up to 10
void topten::updatingTableSize(cards temp)
{
	int temp_size = temp.size();
	int open_table_size = open_table.size();
		
	for(int r = 0; r< open_table_size; r++)
	{
		for(int c = 0; c < temp_size; c++)
		{
			if(open_table[r] ==  temp[c])
			{
				open_table.erase(open_table.begin()+r);
				open_table_size = open_table.size();
				r = r - 2;
				break;
			}
		}
		if(r < -1)r = -1;
	}
}


//print results
void topten::results()
{
	
	printSummary();
	printOpenTablecards();
	addCardsToPlayedLast();
	printPlayer1Stack();
	printPlayer2Stack();
	
	//calculate player 1 points
	int player1_totalpoints = points(player_1Cards);
	//calculate player 2 points
	int player2_totalpoints = points(player_2Cards);
	
	//print the score and the winner
	printScore(player1_totalpoints, player2_totalpoints);
}

//print each players total cards. 
//print open table size and cardlist size
void topten::printSummary()
{	
	std::string blue_color = "\033[1;34m";
	std::cout<<blue_color<<"Player 1 cards in total: "<<player_1Cards.size()<<"\n";
	std::cout<<"Player 2 cards in total: "<<player_2Cards.size()<<"\n";
	std::cout<<"cardlist left in total: "<<cardlist.size()<<"\n";
	std::cout<<"open table cards in total: "<<open_table.size()<<"\n";
}

//print cards left in the open table
void topten::printOpenTablecards()
{
	int open_table_size = open_table.size();
	if(open_table_size != 0)
		std::cout<<"open table cards \n";
	for(int r = 0; r < open_table_size; r++)
		std::cout<<open_table[r].first<<" "<<open_table[r].second<<std::endl;
}


//print player 1 stack
void topten::printPlayer1Stack()
{
	int player1Card_size = player_1Cards.size();
	std::cout<<"Player 1 cards \n";
	for(int r = 0; r < player1Card_size; r++)
		std::cout<<player_1Cards[r].first<<" "<<player_1Cards[r].second<<std::endl;
}

//print player 2 stack
void topten::printPlayer2Stack()
{
	int player2Card_size = player_2Cards.size();
	std::cout<<"Player 2 cards \n";
	for(int r = 0; r < player2Card_size; r++)
		std::cout<<player_2Cards[r].first<<" "<<player_2Cards[r].second<<std::endl;
}


//print the score of two players
void topten::printScore(int player1_totalpoints, int player2_totalpoints)
{
	if(player1_totalpoints > player2_totalpoints)
	{
		std::cout<<"Player 1 score: "<<player1_totalpoints<<" Won🤸🤸🤸🤸 \n";
		std::cout<<"Player 2 score: "<<player2_totalpoints<<" lost🤔 \n";
	}
	else
	{
		std::cout<<"Player 2 score: "<<player2_totalpoints<<" Won🤸🤸🤸🤸 \n";
		std::cout<<"Player 1 score: "<<player1_totalpoints<<" lost🤔 \n";
	}
}


//add cards from open table to the last player
void topten::addCardsToPlayedLast()
{
	int open_table_size = open_table.size();
	
	if((player_1 and repeat) or (player_2 and !repeat))
	{
		for(int r = 0; r < open_table_size; r++)
			player_1Cards.push_back(open_table[r]);
	}
	else
	{
		for(int r = 0; r < open_table_size; r++)
			player_2Cards.push_back(open_table[r]);	
	}
}


//calculate point for each player
int topten::points(cards temp)
{
	int totalpoints = 0;
	int temp_size = temp.size();
	int count_spade = 0;
	
	if(temp_size != 0)
	{
		for(int r = 0; r <temp_size; r++)
		{
			if(temp[r].first == 1)
			{
				totalpoints += 1;//1 count one point
			}
			else if(temp[r].first == 2 and temp[r].second == "spade")
			{
				totalpoints += 1;//2 spade count one point
			}
			else if(temp[r].first == 10 and temp[r].second == "diamond")
			{
				totalpoints += 2;//10 diamond counts 2 points
			}
			
			//if player got 5 spades, one point is added
			//if player got more than 5 spades two points are added
			if(temp[r].second == "spade")
				count_spade++;
		}
	}
	
	if(count_spade == 5)
		totalpoints +=1;
	else if(count_spade > 5)
		totalpoints +=2;
	
	//if total number of cards in players stack is 20 one point is added
	//if total number of cards in players stack is more than 20 two points are added
	if(temp_size == 20)
		totalpoints +=1;
	else if(temp_size > 20)
		totalpoints +=2;
	
	return totalpoints;
}

//random card selected in cardlist
std::pair<int, std::string> topten::cardInfo(int random_pos)
{
	std::pair<int, std::string> card_opened;
	card_opened = cardlist[random_pos];//get card in random position
	cardlist.erase(cardlist.begin() + random_pos);//remove card select in the list
	card_stack = cardlist.size();//update cardlist size
	return card_opened;
}


//next player 
void topten::next_player()
{
	if(player_1 and !repeat)
	{
		std::cout<<"*********************** player 1 turn ***********************\n";
		player_1 = false;
		player_2 = true; 
	}
	else if(player_2 and !repeat)
	{
		std::cout<<"*********************** player 2 turn*********************** \n";
		player_1 = true;
		player_2 = false; 	
	}
	else if(player_1 and repeat)
	{
		std::cout<<"***********************player 1 repeat************************ \n";
		player_1 = true;
		player_2 = false; 	
	}
	else if(player_2 and repeat)
	{
		std::cout<<"***********************player 2 repeat*********************** \n";
		player_1 = false;
		player_2 = true;
	}
}
