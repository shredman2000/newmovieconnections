import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import './HomePage.css'
function HomePage() {
    const navigate = useNavigate();
    const [clues, setClues] = useState([])
    const todayStr = new Date().toISOString().slice(0, 10);
    const [gameDate, setGameDate] = useState(todayStr);

    const [game, setGame] = useState(null)

    const [selectedTiles, setSelectedTiles] = useState([])
    const [solvedMovies, setSolvedMovies] = useState([]);

    async function getGame(date) {

        try {
            const response = await fetch('http://localhost:8080/api/games/getGame', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ date: gameDate })
            });
            const data = await response.json();
            console.log("retrieved data: ", data);
            setGame(data);
        } catch (err) {
            console.error("Error retrieving game: ", err)
        }
    }

    function updateClues(game) {
        if (!game) { 
            return Array(16).fill({url: "", movieIndex: null, id: null })
        }
        const newClues = [];
        //populate new clues and tag the clues with movie index and id 
        game.movies.forEach((movie, movieIndex) => {
            movie.clues.forEach((clue, clueIndex) => {
                newClues.push({
                    url: clue.url,
                    movieIndex,
                    id: `${movieIndex}-${clueIndex}`
                });
            })
        })

        // shuffle clues
        for (let i = newClues.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [newClues[i], newClues[j]] = [newClues[j], newClues[i]]
        }
        console.log("new clues: ", newClues);
        return newClues;
    
    }
    function handleTileClick(index) {
        setSelectedTiles(prev => {
            if (prev.includes(index)) {
                return prev.filter(i => i !== index); // remove if tile is already selected
            }
            else if (prev.length === 4) {
                return [...prev];
            }
            else {
                return [...prev, index];
            }
        })
    }

    function handleSubmit() {
        if (selectedTiles.length !== 4) {
            return;
        }
        // get the actual clues from the tiles
        const selectedClues = selectedTiles.map(i => clues[i]);


        // first we check how many matches there were per movie, and return the best match,
        // for example if the user got 2 clues right for a movie we would tell me that 2 of the 4 were correct
        const matchCounts = game.movies.map((movie, movieIndex) => {
            const count = selectedClues.filter(c => c.movieIndex === movieIndex).length;
            return {title: movie.title, count}
        })

        console.log("Match counts: ", matchCounts);
        // check if they got all 4 right for a movie
        const allCorrect = matchCounts.some(m => m.count === 4);

        // handle moving tiles to top row and revealing the movie name.
        if (allCorrect) {

        }
        else {
            const bestMatch = matchCounts.reduce((prev, curr) => (curr.count > prev.count ? curr : prev))
            alert(`Clues matched a movie: ${bestMatch.count}`);
        }
    }

    useEffect(() => {
        getGame(gameDate);
    }, [gameDate])

    useEffect(() => {
        setClues(updateClues(game));
    }, [game])


    return (
        <>
        <div className='main-page-wrapper'>
            <div className='content-wrapper'>
                <h1 className='main-page-title'>Movie Connections</h1>

                <div className='board-container'>
                {clues.map((clue, index) => (
                    <div key={index} className={`clue-tile ${selectedTiles.includes(index) ? 'selected' : ''}`} onClick={() => handleTileClick(index)}>
                        <img src={clue.url} alt={`clue ${index + 1}`}/>
                    </div>
                ))}
                </div>
                <button className='submit-button' onClick={() => handleSubmit()}>Submit</button>
            </div>
            
            <button className='admin-button' onClick={() => navigate('/admin')}>Admin</button>
        </div>
        </>
    )
}

export default HomePage;