package com.sdr.notes.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sdr.notes.R
import com.sdr.notes.ui.theme.NotesTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    noteId: Long,
    assistedFactory: DetailAssistedFactory,
    navigateUp: () -> Unit
) {
    val viewModel = viewModel(
        modelClass = DetailViewModel::class.java,
        factory = DetailedViewModelFactory(
            noteId = noteId,
            assistedFactory = assistedFactory
        )
    )

    val state = viewModel.state
    DetailScreen(
        modifier = modifier,
        title = state.title,
        content = state.content,
        isBookMark = state.isBookmark,
        onBookmarkChange = viewModel::onBookMarkChange,
        onTitleChange = viewModel::onTitleChange,
        onContentChange = viewModel::onContentChange,
        onNavigate = {
            if (viewModel.isFormNotBlank) viewModel.addOrUpdateNote()
            navigateUp()
        }
    )
}

@Composable
private fun DetailScreen(
    modifier: Modifier,
    title: String,
    content: String,
    isBookMark: Boolean,
    onBookmarkChange: (Boolean) -> Unit,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onNavigate: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TopSection(
            isBookMark = isBookMark,
            onBookmarkChange = onBookmarkChange,
            onNavigate = onNavigate
        )

        NotesTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            label = stringResource(R.string.title_add_note),
            labelAligment = TextAlign.Start,
            inputTextStyle = MaterialTheme.typography.titleLarge,
            placeholderTextStyle = MaterialTheme.typography.titleLarge,
        )

        NotesTextField(
            modifier = Modifier.fillMaxSize(),
            value = content,
            onValueChange = onContentChange,
            label = stringResource(R.string.content_add_note),

            )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    isBookMark: Boolean,
    onBookmarkChange: (Boolean) -> Unit,
    onNavigate: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .size(48.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(MaterialTheme.colorScheme.surfaceVariant),
            onClick = onNavigate
        ) {
            Icon(painter = painterResource(id = R.drawable.arrow_left), contentDescription = null)
        }
        IconButton(
            modifier = Modifier
                .size(48.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(MaterialTheme.colorScheme.surfaceVariant),
            onClick = { onBookmarkChange(!isBookMark) }
        ) {
            val icon = if (isBookMark) painterResource(id = R.drawable.bookmark_filled)
            else painterResource(id = R.drawable.bookmark)
            Icon(painter = icon, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    labelAligment: TextAlign? = null,
    inputTextStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    placeholderTextStyle: TextStyle = MaterialTheme.typography.bodyLarge,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = inputTextStyle,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = label,
                textAlign = labelAligment,
                modifier = modifier.fillMaxWidth(),
                style = placeholderTextStyle
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPrev() {
    NotesTheme {
        DetailScreen(
            modifier = Modifier,
            title = "Заголовок",
            content = "",
            isBookMark = false,
            onBookmarkChange = {},
            onTitleChange = {},
            onContentChange = {},
            onNavigate = {}
        )
    }
}