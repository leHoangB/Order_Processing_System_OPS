CREATE DATABASE [Order_Processing_System(OPS)]
GO
USE [Order_Processing_System(OPS)]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 5/15/2022 1:02:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[customerID] [int] IDENTITY(1,1) NOT NULL,
	[customerName] [nvarchar](50) NOT NULL,
	[address] [nvarchar](50) NOT NULL,
	[phone] [int] NOT NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[customerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 5/15/2022 1:02:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[customerID] [int] NOT NULL,
	[customerName] [nvarchar](50) NOT NULL,
	[productID] [int] NOT NULL,
	[amount] [real] NOT NULL,
	[orderDate] [date] NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 5/15/2022 1:02:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productID] [int] IDENTITY(1,1) NOT NULL,
	[productPrice] [real] NOT NULL,
	[productType] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Stock]    Script Date: 5/15/2022 1:02:03 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Stock](
	[productID] [int] NOT NULL,
	[quatity] [int] NOT NULL,
	[shopNo] [int] NOT NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([productID], [productPrice], [productType]) VALUES (3, 100, N'Quần')
INSERT [dbo].[Product] ([productID], [productPrice], [productType]) VALUES (4, 110, N'Áo')
INSERT [dbo].[Product] ([productID], [productPrice], [productType]) VALUES (5, 120, N'Giày')
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (3, 10, 1)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (4, 11, 1)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (5, 12, 1)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (3, 13, 2)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (4, 14, 2)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (5, 15, 2)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (3, 16, 3)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (4, 17, 3)
INSERT [dbo].[Stock] ([productID], [quatity], [shopNo]) VALUES (5, 18, 3)
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Order] FOREIGN KEY([orderID])
REFERENCES [dbo].[Order] ([orderID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Order]
GO
