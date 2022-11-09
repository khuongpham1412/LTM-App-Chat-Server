USE [master]
GO
/****** Object:  Database [SocialChat]    Script Date: 11/3/2022 3:21:54 PM ******/
CREATE DATABASE [SocialChat]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SocialChat', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SocialChat.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SocialChat_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\SocialChat_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SocialChat] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SocialChat].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SocialChat] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SocialChat] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SocialChat] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SocialChat] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SocialChat] SET ARITHABORT OFF 
GO
ALTER DATABASE [SocialChat] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SocialChat] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SocialChat] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SocialChat] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SocialChat] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SocialChat] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SocialChat] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SocialChat] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SocialChat] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SocialChat] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SocialChat] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SocialChat] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SocialChat] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SocialChat] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SocialChat] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SocialChat] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SocialChat] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SocialChat] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SocialChat] SET  MULTI_USER 
GO
ALTER DATABASE [SocialChat] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SocialChat] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SocialChat] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SocialChat] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SocialChat] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SocialChat] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SocialChat] SET QUERY_STORE = OFF
GO
USE [SocialChat]
GO
/****** Object:  Table [dbo].[tbl_account]    Script Date: 11/3/2022 3:21:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_account](
	[id] [nvarchar](250) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tbl_account] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_message]    Script Date: 11/3/2022 3:21:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_message](
	[id] [nvarchar](250) NOT NULL,
	[message] [nvarchar](250) NOT NULL,
	[type] [nvarchar](50) NOT NULL,
	[date_send] [datetime] NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[room_id] [nvarchar](250) NOT NULL,
	[user_send_id] [nvarchar](250) NULL,
	[user_received_id] [nvarchar](250) NULL,
 CONSTRAINT [PK_tbl_message] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_room]    Script Date: 11/3/2022 3:21:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_room](
	[room_id] [nvarchar](250) NOT NULL,
	[user_send_id] [int] NOT NULL,
	[user_received_id] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_user_room]    Script Date: 11/3/2022 3:21:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_user_room](
	[user_id] [nvarchar](250) NOT NULL,
	[room_id] [nvarchar](250) NOT NULL,
	[type] [nvarchar](50) NOT NULL,
	[image] [nvarchar](50) NULL,
	[name] [nvarchar](50) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[tbl_account] ([id], [username], [password]) VALUES (N'1', N'khuong', N'123')
INSERT [dbo].[tbl_account] ([id], [username], [password]) VALUES (N'2', N'kiet', N'123')
INSERT [dbo].[tbl_account] ([id], [username], [password]) VALUES (N'3', N'khanh', N'123')
INSERT [dbo].[tbl_account] ([id], [username], [password]) VALUES (N'4', N'khoa', N'123')
GO
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'008b5949-ea9f-4ccb-ad3b-0b4a5e63f40b', N'jnk', N'TEXT', CAST(N'2022-10-22T03:08:34.783' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'009d2fa6-8205-49a5-9f1e-de9c1bcf5f7b', N'a;oolkdjadfiugfhjabfhsaf', N'TEXT', CAST(N'2022-10-27T17:21:16.140' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'032a7464-16c1-4d3d-a65a-45ed257e3eb4', N'yyhj', N'TEXT', CAST(N'2022-10-20T16:29:52.313' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'0392d937-e655-48c3-b93c-8c5790bb6c6d', N'cferbenler', N'TEXT', CAST(N'2022-10-27T16:11:59.183' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'06ee37b1-071c-449e-8ab4-b092e931224f', N'hiiiiiiiiiiiiiiii', N'TEXT', CAST(N'2022-11-02T13:51:56.417' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'0719b41e-9fd9-4e4c-8267-60576e369686', N'hi', N'TEXT', CAST(N'2022-10-27T16:49:32.780' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'0784604f-3ca0-4df8-a173-258a9a6f7e50', N'khuong ngfdsasdfgaxhgfd', N'TEXT', CAST(N'2022-10-29T21:14:17.937' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'0b4cd478-8046-458a-9bba-289540881b74', N'khuong oi kiet goi', N'TEXT', CAST(N'2022-10-28T22:13:34.657' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'2', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'0e4783b8-12fd-4785-8e97-96203c7f56da', N'gi cho con', N'TEXT', CAST(N'2022-10-27T18:57:51.337' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'0ea370cd-65de-4bda-bf89-e030a699ffd1', N'khoa thu test', N'TEXT', CAST(N'2022-10-27T18:58:25.637' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'101ec9e9-da08-4673-8e8c-d8605cad4444', N'cijoj', N'TEXT', CAST(N'2022-10-20T23:22:32.460' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'11f2c9bb-51a4-44b3-8195-97b490b35c51', N'hiiiii', N'TEXT', CAST(N'2022-10-20T19:15:55.110' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'2', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'13b1cddf-7470-4666-b881-94d9746c84d4', N'gyher', N'TEXT', CAST(N'2022-10-21T23:03:31.370' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'166caf0a-4641-49a9-984b-46fcaa3107d5', N'weqw', N'TEXT', CAST(N'2022-11-02T13:53:17.760' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'17dbbf89-8be3-48b6-9493-e3f34fa6aca2', N'khuong', N'TEXT', CAST(N'2022-10-29T21:58:12.677' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'17eec38c-b909-409f-9998-a2436b3a53a6', N'hello', N'TEXT', CAST(N'2022-11-02T13:49:45.523' AS DateTime), N'SEEN', N'ccaa6a55-40b6-4886-b74d-85b9065d1598', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'186453b1-d325-492e-9a02-d70ab09c5ba3', N'tyhtg', N'TEXT', CAST(N'2022-10-27T18:16:39.393' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'194aa800-6595-4075-b907-61a6d121faf4', N'khaaaaa', N'TEXT', CAST(N'2022-10-27T16:24:55.340' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'1990a08b-ba07-4fd5-bf78-0d4840eb17a5', N'gherhj', N'TEXT', CAST(N'2022-10-29T05:11:29.290' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'2', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'1cb4aa02-d5a6-40a0-8af3-ddef3137aaf8', N'xin chao', N'TEXT', CAST(N'2022-10-20T23:20:54.653' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'1d364860-463e-4c53-b0c3-c3dc9d9d6cd1', N'hello', N'TEXT', CAST(N'2022-10-20T23:20:56.930' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'1e8feb3d-ae4a-43f9-a3eb-541e3e0b85d9', N'asdddfghj', N'TEXT', CAST(N'2022-10-27T18:22:58.447' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'203894ac-f4af-48e6-a072-50f2c6581a1b', N'chao', N'TEXT', CAST(N'2022-10-27T16:07:03.007' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'246bf82b-5c48-4e2b-8cf8-72dd227612bb', N'khoa nhan', N'TEXT', CAST(N'2022-10-29T21:16:43.457' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'2743a422-945c-4971-aa5c-ed94991b9a88', N'chao', N'TEXT', CAST(N'2022-10-29T21:22:13.760' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'2956f0f4-abac-4ec4-ba2b-c9f7e1153908', N'hiiiiiiiifhewfew', N'TEXT', CAST(N'2022-10-29T21:44:39.223' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'333e25c1-a8d5-47ce-bfeb-618b8a8b1d70', N'hiiiiii', N'TEXT', CAST(N'2022-10-29T21:34:41.797' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'4ab77930-df80-48a0-8dae-add8ace375e9', N'skdjs', N'TEXT', CAST(N'2022-11-02T13:53:38.690' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'4bab8bbc-135b-499f-ba30-282f639706e4', N'asdfghj', N'TEXT', CAST(N'2022-10-29T20:28:53.887' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'4e72449f-9f3f-41e6-a5f0-a87ed21bdaac', N'kiet nhan', N'TEXT', CAST(N'2022-10-29T21:11:49.910' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'2', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'4f1c230b-e0a5-4982-aeac-d274e844721f', N'eee m', N'TEXT', CAST(N'2022-10-28T22:17:28.863' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'5dbf68ae-4961-44c4-964c-068a9aa325e4', N'hi', N'TEXT', CAST(N'2022-10-29T21:18:52.550' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'5e0ab371-365f-42c5-8b08-251b1b49aebb', N'sao m', N'TEXT', CAST(N'2022-10-29T21:17:06.853' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'63b5c5e1-39d9-46f8-b67a-21e03ca47638', N'sdfghjkl', N'TEXT', CAST(N'2022-10-29T21:14:40.737' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'4', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'63cc1df4-07c9-4f86-8692-709cab038849', N'utf8', N'TEXT', CAST(N'2022-11-02T13:53:01.543' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'66c74db5-362f-43e4-9488-2c51cdc44d31', N'khuong nhan lai group', N'TEXT', CAST(N'2022-10-29T21:55:18.597' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'68e295dd-edb2-4699-8454-79d4498b5f1a', N'khoa nhan', N'TEXT', CAST(N'2022-10-29T21:18:42.830' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'692ff930-a9a9-436c-9083-f7cc606191e0', N'oiiii', N'TEXT', CAST(N'2022-10-28T22:14:11.827' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'6999fc9c-da7f-45a7-8713-f947ed19c52b', N'khuong oi', N'TEXT', CAST(N'2022-10-29T05:10:37.347' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'2', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'6dab9e5f-1602-4c22-9285-55b8a3cf0939', N'sdfgbhn', N'TEXT', CAST(N'2022-10-29T21:51:56.223' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'6ec930ed-53fb-4ce0-9a9e-13d81cd56927', N'khuong nhan', N'TEXT', CAST(N'2022-10-29T21:42:58.133' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'70a638c0-0ee4-4720-990b-f57a5c47db53', N'ghebr', N'TEXT', CAST(N'2022-10-29T21:33:27.237' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'7222100d-d318-4596-a3e8-97b9c8f8738b', N'khoa nhan khoa', N'TEXT', CAST(N'2022-10-29T21:53:22.637' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'77db03c1-e616-47c7-9ae0-b3390ab9bd0b', N'cfegvhjbjcjkebcejkrbcerjbrjkfbrekjnrnf', N'TEXT', CAST(N'2022-10-27T18:56:55.950' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'78e1983a-cfd2-4514-908b-2438d51f392f', N'no way', N'TEXT', CAST(N'2022-11-02T13:55:37.743' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'2', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'7aeae252-ec68-461c-bd8e-d5769f12a98c', N'dfvghbnj', N'TEXT', CAST(N'2022-10-29T21:04:45.150' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'8a6afaf1-2231-41f4-9487-d3f44a5a00c1', N'gẻgerg', N'TEXT', CAST(N'2022-10-28T22:17:42.350' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'8c2be5eb-daa1-4f2d-abd4-5723bd20a2f8', N'hellu', N'TEXT', CAST(N'2022-11-02T13:56:02.027' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'2', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'916fc117-2c34-46d7-9dbe-cacea12a502f', N'hi', N'TEXT', CAST(N'2022-10-29T20:30:27.790' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'4', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'962632b9-7286-4b4c-9978-4a3292c67365', N'khuong nhan group', N'TEXT', CAST(N'2022-10-29T21:54:52.597' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'9b568d1a-62c9-4b26-a586-6154c8dac470', N'khuong nhan', N'TEXT', CAST(N'2022-10-29T21:22:49.440' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'9c97500f-c415-4aa5-b622-7e0da28d920b', N'khuong nhan group', N'TEXT', CAST(N'2022-10-29T21:57:53.587' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'9d715ad7-9b8b-4293-9be1-c1e4d70beac8', N'khuong oiiiii', N'TEXT', CAST(N'2022-10-29T05:11:04.187' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'2', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'a151d108-b342-41ed-a909-935f63d033af', N'khoa nhan group', N'TEXT', CAST(N'2022-10-29T22:00:32.397' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'4', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'a1ca37b4-7b06-4e24-8bbc-c10509bc3523', N'hello keit', N'TEXT', CAST(N'2022-11-02T13:50:49.433' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'a1f5a24d-0747-4d80-aef4-df019c17366b', N'khuong nhan group', N'TEXT', CAST(N'2022-10-29T22:00:56.213' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'afa245c4-a406-4c5a-a415-162d806c6e6f', N'hiiii', N'TEXT', CAST(N'2022-10-29T21:00:23.337' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'b5b33a2f-1c71-4006-8a95-9c8a5ce53ed2', N'jyh', N'TEXT', CAST(N'2022-10-29T20:29:44.857' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'b90d14cd-30fc-403c-9ed3-f050c454a372', N'sdfghjhghfrsdtfgyhj', N'TEXT', CAST(N'2022-10-29T21:40:36.953' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'c47b043b-f465-4ded-a8c4-cbee33e58452', N'khuong nhan', N'TEXT', CAST(N'2022-10-29T21:09:21.970' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'1', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'c8ce828b-b547-4177-a995-1c92e300d8c5', N'hi', N'TEXT', CAST(N'2022-10-29T21:17:31.323' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'ca8f33cf-261b-44ef-bb7d-69ac01e2aef8', N'fnew', N'TEXT', CAST(N'2022-11-02T13:54:07.297' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'cad14bfe-498e-46d2-b575-3a26fa4e8f83', N'ufhjfnkjfewnf', N'TEXT', CAST(N'2022-11-02T13:52:32.550' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'cf0ae583-b1ea-4024-ab6c-e5e32ab4d78f', N'khoa nhan', N'TEXT', CAST(N'2022-10-29T21:43:08.173' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'cfcf308d-d9d1-4bba-aa6d-b4cbefc55850', N'khuong nahn', N'TEXT', CAST(N'2022-10-29T21:27:54.537' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'd2e22ee0-088a-4706-8ee3-220bc41a998a', N'khuong nhan tiep', N'TEXT', CAST(N'2022-10-29T21:53:52.767' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'd658ad0e-3cca-4315-a8a1-c96fd099c301', N'hi', N'TEXT', CAST(N'2022-10-29T21:23:14.627' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'4', N'4')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'e1d9e62a-a835-47e4-9b4b-6b2190566db3', N'khuong nhan kiet', N'TEXT', CAST(N'2022-10-29T21:54:29.417' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'ebd6cf87-2895-4028-95aa-b12f31294977', N'kiet nhan group', N'TEXT', CAST(N'2022-10-29T22:00:44.143' AS DateTime), N'SEEN', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'2', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'edb45bc6-7a8b-417c-873c-3f0fe09f625f', N'hiiiiijfwuihfwejkfnwe', N'TEXT', CAST(N'2022-10-29T21:39:10.307' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'f1bef8a0-5c10-41e4-9ef2-cbb23ce99a02', N'gi kiet', N'TEXT', CAST(N'2022-10-29T05:11:19.800' AS DateTime), N'SEEN', N'45590546-4faf-11ed-bdc3-0242ac120002', N'1', N'2')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'f4397dfd-ac70-4aea-bba9-f607ec3a175f', N'lo cai gi ma lo', N'TEXT', CAST(N'2022-11-02T13:50:14.730' AS DateTime), N'SEEN', N'ccaa6a55-40b6-4886-b74d-85b9065d1598', N'2', N'ALL')
INSERT [dbo].[tbl_message] ([id], [message], [type], [date_send], [status], [room_id], [user_send_id], [user_received_id]) VALUES (N'f8aa0df2-d010-4841-9d18-5672ee8bd0eb', N'hi', N'TEXT', CAST(N'2022-10-29T21:21:25.163' AS DateTime), N'SEEN', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'1', N'1')
GO
INSERT [dbo].[tbl_room] ([room_id], [user_send_id], [user_received_id]) VALUES (N'45590546-4faf-11ed-bdc3-0242ac120002', 1, 2)
GO
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'1', N'45590546-4faf-11ed-bdc3-0242ac120002', N'PRIVATE', NULL, N'Vu Ba Kiet')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'2', N'45590546-4faf-11ed-bdc3-0242ac120002', N'PRIVATE', NULL, N'Pham Duy Khuong')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'4', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'PRIVATE', NULL, N'Pham Duy Khuong')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'1', N'9a9f2ad2-4fc6-11ed-bdc3-0242ac120002', N'PRIVATE', NULL, N'Tran Dang Khoa')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'1', N'9358e7dd-08c6-4b77-9bb4-713ce0b3f278', N'PUBLIC', N'', N'TEST Name')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'2', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'PUBLIC', NULL, N'Phong Chat 1')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'2', N'ccaa6a55-40b6-4886-b74d-85b9065d1598', N'PUBLIC', N'', N'TEST Name')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'3', N'ccaa6a55-40b6-4886-b74d-85b9065d1598', N'PUBLIC', N'', N'TEST Name')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'1', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'PUBLIC', NULL, N'Phong Chat 1')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'4', N'6cca5647-c805-4de9-9edb-9f5c7ad773b6', N'PUBLIC', NULL, N'Phong Chat 1')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'1', N'ccaa6a55-40b6-4886-b74d-85b9065d1598', N'PUBLIC', N'', N'TEST Name')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'2', N'9358e7dd-08c6-4b77-9bb4-713ce0b3f278', N'PUBLIC', N'', N'TEST Name')
INSERT [dbo].[tbl_user_room] ([user_id], [room_id], [type], [image], [name]) VALUES (N'3', N'9358e7dd-08c6-4b77-9bb4-713ce0b3f278', N'PUBLIC', N'', N'TEST Name')
GO
USE [master]
GO
ALTER DATABASE [SocialChat] SET  READ_WRITE 
GO
