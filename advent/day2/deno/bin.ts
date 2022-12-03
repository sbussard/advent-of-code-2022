// How awkward, this deno rule bypasses bazel's dependency management system
// $ bazel query "deps(//advent/day2/deno:bin)" # does not show this dependency
import { BufReader } from "https://deno.land/std@0.167.0/io/mod.ts";
import { parse } from "https://deno.land/std@0.119.0/flags/mod.ts";
const { part } = parse(Deno.args, { string: ["part"] });

// START COMMON STUFF ======================================================================
// @aspect_rules_deno is still unreliable so instead of making a common library, it's all together here
//
// Use a modified DoublyLinkedList
// each node represents a response
// each node's .next points to the node representing the response that beats it
// So A -> B -> C -> A (the node at the beginning)
interface INode {
  value: string;
  points: number;
  losesTo?: INode;
  winsAgainst?: INode;
}

const Node = (value: INode['value'], points: INode['points']): INode => ({ value, points });
const A = Node('Rock', 1);
const B = Node('Paper', 2);
const C = Node('Scissors', 3);
A.losesTo = B;
B.winsAgainst = A;
B.losesTo = C;
C.winsAgainst = B;
C.losesTo = A;
A.winsAgainst = C;

// Letter types
type ABC = "A" | "B" | "C";
type XYZ = "X" | "Y" | "Z";

// An easy way to get the right node
// Syntactic sugar ftw!
const abcToNode: { [key in ABC]: INode } = { A, B, C };

const WIN_SCORE = 6;
const DRAW_SCORE = 3;
const LOSE_SCORE = 0;

// returns [p1 score, p2 score]
function calculateScores(p1: INode, p2: INode) {
  if (p1.winsAgainst === p2) return [p1.points + WIN_SCORE, p2.points + LOSE_SCORE]; // p1 wins
  if (p2.winsAgainst === p1) return [p1.points + LOSE_SCORE, p2.points + WIN_SCORE]; // p2 wins
  return [p1.points + DRAW_SCORE, p2.points + DRAW_SCORE]; // draw
}
// END COMMON STUFF ======================================================================

// Since the input file is being read directly by the code 
// instead of via CLI argument, it has to be added as a dependency
// in the BUILD file. The path is relative to bazel repo root.
const FILE_PATH = "advent/day2/input.txt";

async function solutionPart1() {
  // Using a streaming approach in case the file size is larger than machine memory
  const file = await Deno.open(FILE_PATH);
  const bufReader = new BufReader(file);

  // Make a map because it's easier to work with just ABC not this XYZ nonsense
  const xyzToAbc: { [key in XYZ]: ABC } = { X: 'A', Y: 'B', Z: 'C' };

  let line;
  let p1, p2; // player1 and player2 moves
  let totalScore = 0;
  while (true) {
    line = await bufReader.readString('\n');
    if (!line) break;
    p1 = abcToNode[line[0] as ABC]; // first character
    p2 = abcToNode[xyzToAbc[line[2] as XYZ]]; // third character, converted from XYZ to ABC
    totalScore += calculateScores(p1, p2)[1];
  }
  file.close();

  console.log(totalScore);
}

async function solutionPart2() {
  // Using a streaming approach in case the file size is larger than machine memory
  const file = await Deno.open(FILE_PATH);
  const bufReader = new BufReader(file);

  // Returns what to play given, what p1 will play, and what outcome is desired
  const getP2Move = (desiredOutcome: XYZ, p1: INode): INode => ({
    X: p1.winsAgainst!,
    Y: p1,
    Z: p1.losesTo!
  })[desiredOutcome];

  let line;
  let p1, p2, desiredOutcome; // player moves and whether to win/lose/tie
  let totalScore = 0;
  while (true) {
    line = await bufReader.readString('\n');
    if (!line) break;
    p1 = abcToNode[line[0] as ABC]; // first character
    desiredOutcome = line[2] as XYZ; // third character
    p2 = getP2Move(desiredOutcome, p1);
    totalScore += calculateScores(p1, p2)[1];
  }
  file.close();

  console.log(totalScore);
}

if (part == 1) solutionPart1();
if (part == 2) solutionPart2();