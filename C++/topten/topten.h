#ifndef TOPTEN_H
#define TOPTEN_H
#include<string>
#include<vector>

class topten
{
  public:
  		topten();
  		void start();
  		typedef std::vector<std::pair<int, std::string>> cards;
  		typedef std::pair<int, std::string> card;
  		
  private:
  		void next_player();
  		void addToPlayers_stack(cards temp);
  		void updatingTableSize(cards temp);
  		void summation(card opened_card);
  		void addCardsToPlayedLast();
  		int points(cards temp);
  		void updatePlayerCards(cards &pl_cards, cards temp);
  		void findOtherSum(cards &pl_cards, cards temp);
  		void results();
  		void printSummary();
  		void printOpenTablecards();
  		void printPlayer1Stack();
  		void printPlayer2Stack();
  		std::string card_type[4] = {"spade","club","diamond","heart"};
  		cards cardlist;
  		void printScore(int player1_totalpoints, int player2_totalpoints);
		card cardInfo(int random_pos);
		int card_stack;
  		void card_position();
  		cards open_table;
		cards player_1Cards;
		cards player_2Cards;
		void firstTime();
		bool firstCondition(int opened_card);
		void repeatOrNot(int sum, card opened_card);
		int doubleCheckSum(card opened_card);
		int minOf1_sumation(card opened_card);
		int minOf2_sumation(card opened_card);
		int minOf3_sumation(card opened_card);
		int minOf4_sumation(card opened_card);
  		bool player_1;
  		bool player_2;
  		const int MaxSum = 10;
  		bool repeat;
  		bool first_time;
};

#include "topten.cpp"
#endif
