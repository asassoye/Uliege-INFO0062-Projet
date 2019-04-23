/*
 * Data provided to solve the soccer ball puzzle. The CONNECTIONS array is a 2D array containing 
 * 32 lines (for 32 facets), each line providing the associated connection numbers seen on 
 * soccer_ball_net.pdf, while ELEMENTS_SIDES and NB_LINES provide respectively the concavity and 
 * convexity of each side of a piece and the amount of occurrences for each different piece. The 
 * pieces are depicted in soccer_ball_pieces.pdf.
 *
 * Using the .length operator on these arrays can be useful to retrieve:
 * -the amount of facets,
 * -the amount of sides of each facet, 
 * -the amount of unique (puzzle) pieces.
 */

public class Data
{
	final static int[][] CONNECTIONS = {
	{  1,  2,  3,  4,  5 },      { 22, 13,  7,  1,  6, 12 },
	{ 14, 23, 15,  8,  2,  7 },  {  8, 16, 24, 17,  9,  3 },
	{  4,  9, 18, 25, 19, 10 },  {  6,  5, 10, 20, 21, 11 },
	{ 27, 12, 11, 26, 36 },      { 48, 49, 28, 22, 27, 37 },
	{ 38, 29, 14, 13, 28 },      { 39, 50, 51, 30, 23, 29 },
	{ 30, 40, 31, 16, 15 },      { 31, 41, 52, 53, 32, 24 },
	{ 17, 32, 42, 33, 18 },      { 25, 33, 43, 54, 55, 34 },
	{ 20, 19, 34, 44, 35 },      { 26, 21, 35, 45, 46, 47 },
	{ 37, 36, 47, 56, 57, 71 },  { 67, 58, 48, 71, 72 },
	{ 59, 73, 39, 38, 49, 58 },  { 74, 68, 60, 50, 73 },
	{ 60, 61, 75, 41, 40, 51 },  { 75, 76, 69, 62, 52 },
	{ 53, 62, 63, 77, 43, 42 },  { 54, 77, 78, 70, 64 },
	{ 44, 55, 64, 65, 79, 45 },  { 56, 46, 79, 80, 66 },
	{ 72, 57, 66, 85, 86, 81 },  { 87, 82, 74, 59, 67, 81 },
	{ 82, 88, 83, 76, 61, 68 },  { 69, 83, 89, 84, 78, 63 }, 
	{ 65, 70, 84, 90, 85, 80 },  { 86, 90, 89, 88, 87 }};
    
	final static int[][] ELEMENTS_SIDES = {
	{  1, -1,  1,  1,  1, -1 },  { -1, -1,  1,  1,  1, -1 },
	{  1,  1, -1,  1,  1, -1 },  {  1, -1,  1, -1,  1, -1 },
	{  1, -1, -1,  1, -1, -1 },  {  1, -1, -1,  1,  1, -1 },
	{ -1, -1, -1,  1,  1, -1 },  { -1, -1,  1,  1,  1,  1 },
	{ -1, -1,  1, -1,  1, -1 },  {  1, -1,  1,  1, -1, -1 },
	{  1, -1, -1, -1,  1 },      {  1,  1, -1, -1,  1 },
	{ -1,  1, -1, -1,  1 },      {  1,  1, -1,  1, -1 }};
    
	final static int[] NB_ELEMENTS = 
	{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3 };
}
