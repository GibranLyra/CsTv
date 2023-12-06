package com.gibranlyra.fuzecctest.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gibranlyra.fuzecctest.R
import com.gibranlyra.fuzecctest.domain.model.PandaImage
import com.gibranlyra.fuzecctest.ui.theme.FuzeccTheme

@Composable
fun TeamView(
    teamImage: PandaImage,
    teamName: String,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal,
    textAlign: TextAlign
) {
    Column(
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.padding_medium)),
        horizontalAlignment = horizontalAlignment
    ) {
        FuzeAsyncImage(
            imageUrl = teamImage.getImage(PandaImage.ImageType.THUMBNAIL),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.large_match_team_image_width))
                .weight(2f),
        )

        FuzeText(
            modifier = Modifier.weight(1f),
            textAlign = textAlign,
            text = teamName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = FuzeTextStyle.BASE_SUBTLE
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun TeamViewPreview() {
    val match = stubMatch(1)
    FuzeccTheme {
        TeamView(
            modifier = Modifier.height(200.dp),
            teamImage = match.team1Image,
            teamName = match.team1Name,
            horizontalAlignment = Alignment.CenterHorizontally,
            textAlign = TextAlign.Center
        )
    }
}