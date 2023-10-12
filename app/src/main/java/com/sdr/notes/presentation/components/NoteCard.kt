package com.sdr.notes.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sdr.notes.R
import com.sdr.notes.data.local.model.Note

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteCard(
    note: Note,
    selected: Boolean = false,
    onSelectedCard: (Long) -> Unit = {},
    onBookmarkChange: (note: Note) -> Unit,
    onDeleteNote: (Long) -> Unit,
    onNoteClicked: (Long) -> Unit
) {
    val haptics = LocalHapticFeedback.current
    val icon = if (note.isBookMarked) painterResource(id = R.drawable.bookmark_filled)
    else painterResource(id = R.drawable.bookmark)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .combinedClickable(
                enabled = true,
                onClick = { onNoteClicked(note.id) },
//                onLongClick = {
//                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
//                    onSelectedCard(note.id)
//                }
            ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = note.content,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
//            AnimatedVisibility(selected) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onDeleteNote(note.id) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = null
                    )
                }

                IconButton(onClick = { onBookmarkChange(note) }) {
                    Icon(
                        painter = icon,
                        contentDescription = null
                    )
                }
            }
//            }

        }
    }
}