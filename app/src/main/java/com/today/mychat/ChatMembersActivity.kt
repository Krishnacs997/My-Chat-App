package com.today.mychat

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.today.mychat.ui.theme.MyChatTheme

class ChatMembersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyChatTheme {
                ChatMembersScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatMembersScreen() {
    val sampleMembers = listOf(
        ChatMember(
            id = "1",
            name = "John Doe",
            email = "john@example.com",
            mobile = "+1234567890",
            isOnline = true,
            lastSeen = "Online",
            profileImageUrl = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=150&h=150&fit=crop&crop=face"
        ),
        ChatMember(
            id = "2",
            name = "Jane Smith",
            email = "jane@example.com",
            mobile = "+1234567891",
            isOnline = true,
            lastSeen = "Online",
            profileImageUrl = "https://images.unsplash.com/photo-1494790108755-2616b612b786?w=150&h=150&fit=crop&crop=face"
        ),
        ChatMember(
            id = "3",
            name = "Mike Johnson",
            email = "mike@example.com",
            mobile = "+1234567892",
            isOnline = false,
            lastSeen = "2 hours ago",
            profileImageUrl = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150&h=150&fit=crop&crop=face"
        ),
        ChatMember(
            id = "4",
            name = "Sarah Wilson",
            email = "sarah@example.com",
            mobile = "+1234567893",
            isOnline = false,
            lastSeen = "1 day ago",
            profileImageUrl = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=150&h=150&fit=crop&crop=face"
        ),
        ChatMember(
            id = "5",
            name = "David Brown",
            email = "david@example.com",
            mobile = "+1234567894",
            isOnline = true,
            lastSeen = "Online",
            profileImageUrl = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=150&h=150&fit=crop&crop=face"
        ),
        ChatMember(
            id = "6",
            name = "Emily Davis",
            email = "emily@example.com",
            mobile = "+1234567895",
            isOnline = false,
            lastSeen = "3 hours ago",
            profileImageUrl = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=150&h=150&fit=crop&crop=face"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat Members") },
                navigationIcon = {
                    IconButton(onClick = { 
                        // Go back to previous activity
                        this@ChatMembersActivity.finish()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sampleMembers) { member ->
                ChatMemberItem(member = member)
            }
        }
    }
}

@Composable
fun ChatMemberItem(member: ChatMember) {
    val context = LocalContext.current
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            val intent = Intent(context, ChatDetailActivity::class.java)
            intent.putExtra("member", member)
            context.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image with Online Status
            Box {
                AsyncImage(
                    model = member.profileImageUrl,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    error = rememberAsyncImagePainter(
                        model = "https://via.placeholder.com/150/cccccc/ffffff?text=${member.name.first()}"
                    )
                )
                
                // Online Status Indicator
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(
                            if (member.isOnline) Color.Green else Color.Gray
                        )
                        .align(Alignment.BottomEnd)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Member Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = member.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = if (member.isOnline) "Online" else member.lastSeen,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (member.isOnline) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatMemberItemPreview() {
    MyChatTheme {
        ChatMemberItem(
            member = ChatMember(
                id = "1",
                name = "John Doe",
                email = "john@example.com",
                mobile = "+1234567890",
                isOnline = true,
                lastSeen = "Online"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatMembersScreenPreview() {
    MyChatTheme {
        ChatMembersScreen()
    }
}
