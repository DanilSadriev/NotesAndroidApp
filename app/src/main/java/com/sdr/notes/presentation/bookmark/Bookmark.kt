package com.sdr.notes.presentation.bookmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sdr.notes.R
import com.sdr.notes.common.ScreenViewState
import com.sdr.notes.data.local.model.Note
import com.sdr.notes.presentation.components.NoteCard
import com.sdr.notes.ui.theme.NotesTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    modifier: Modifier = Modifier,
    onBookMarkChange: (note: Note) -> Unit,
    onDelete: (Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {
    when (state.notes) {
        is ScreenViewState.Loading -> {
            CircularProgressIndicator()
        }

        is ScreenViewState.Succes -> {
            val notes = state.notes.data
            Scaffold(
                topBar = {
                    TopAppBar(
                        windowInsets = WindowInsets(
                            left = 0.dp,
                            top = 0.dp,
                            right = 0.dp,
                            bottom = 0.dp
                        ),
                        title = {
                            Text(
                                text = stringResource(R.string.app_bar_bookmark_title),
                            )
                        }
                    )
                },
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(4.dp),
                    modifier = modifier.padding(it)
                ) {
                    items(notes) { note ->
                        NoteCard(
                            note = note,
                            onBookmarkChange = onBookMarkChange,
                            onDeleteNote = onDelete,
                            onNoteClicked = onNoteClick
                        )
                    }
                }
            }

        }

        is ScreenViewState.Error -> {
            Text(
                text = state.notes.message ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookmarkScreenPrev() {
    val notes = listOf<Note>(
        Note(
            0,
            "Не следует забывать, что в провинциях ещё есть чем поживиться",
            "Test",
            createdDate = Date(),
            isBookMarked = false
        ),
        Note(
            0,
            "Заголовок",
            "Но реплицированные с зарубежных источников, современные исследования, превозмогая сложившуюся непростую экономическую ситуацию, в равной степени предоставлены сами себе. Являясь всего лишь частью общей картины, реплицированные с зарубежных источников, современные исследования, превозмогая сложившуюся непростую экономическую ситуацию, подвергнуты целой серии независимых исследований. Не следует, однако, забывать, что высокое качество позиционных исследований влечет за собой процесс внедрения и модернизации благоприятных перспектив.",
            createdDate = Date(),
            isBookMarked = false
        ),
        Note(
            0,
            "Заголовок",
            "Есть над чем задуматься: элементы политического процесса формируют глобальную экономическую сеть и при этом — превращены в посмешище, хотя само их существование приносит несомненную пользу обществу.",
            createdDate = Date(),
            isBookMarked = true
        ),
        Note(
            0,
            "Не следует забывать, что в провинциях ещё есть чем поживиться",
            "С другой стороны, укрепление и развитие внутренней структуры напрямую зависит от анализа существующих паттернов поведения. В частности, экономическая повестка сегодняшнего дня, а также свежий взгляд на привычные вещи — безусловно открывает новые горизонты для глубокомысленных рассуждений.",
            createdDate = Date(),
            isBookMarked = false
        ),
        Note(
            0,
            "Как бы то ни было, неподкупность государственных СМИ разочаровала",
            "Противоположная точка зрения подразумевает, что непосредственные участники технического прогресса своевременно верифицированы.",
            createdDate = Date(),
            isBookMarked = false

        )
    )
    NotesTheme {
        BookmarkScreen(
            state = BookmarkState(notes = ScreenViewState.Succes(notes)),
            onBookMarkChange = {},
            onDelete = {},
            onNoteClick = {}
        )
    }
}