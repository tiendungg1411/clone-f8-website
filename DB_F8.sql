-- drop database db_f8;
CREATE DATABASE DB_F8;
USE DB_F8;
create table Setting (
	id int primary key,
    actor nvarchar(100)
);
create table Account (
	id int AUTO_INCREMENT  primary key,
    userName nvarchar(100),
    password nvarchar(100),
    phone nvarchar(100),
    mail nvarchar(100),
    googleId nvarchar(100),
    facebookId nvarchar(100),
    githubId nvarchar(100),
    bio nvarchar(500),
    avatar nvarchar(500),
    url nvarchar(100),
    verificationCode nvarchar(100),
    registrationDate nvarchar(100),
    firstAndLastName nvarchar(100),
    coverImage nvarchar(300),
    roleID int,
    foreign key (roleID) references Setting(id)
);

create table Blog(
	id int primary key,
    title nvarchar(100),
    topic nvarchar(100),
    stateID int,
    content nvarchar(1000),
    numOfLikes nvarchar(100),
    date nvarchar(100),
    userID int,
    foreign key (userID) references Account(id)
);
create table SavedBlog(
	id int primary key,
    blogID int,
    userID int,
	time nvarchar(100),
    foreign key (blogID) references Blog(id),
    foreign key (userID) references Account(id)
);
create table LikeOfBlog(
	id int AUTO_INCREMENT primary key,
    userID int,
    foreign key (userID) references Account(id),
    blogID int,
    foreign key (blogID) references Blog(id)
);

Create table CommentDetail(
	id int AUTO_INCREMENT primary key,
    content nvarchar(200),
    date nvarchar(100),
    stateID int,
    userID int,
    foreign key (userID) references Account(id),
    blogID int,
    foreign key (blogID) references Blog(id)
);

create table RouteType(
	id int primary key,
    name nvarchar(100),
    image nvarchar(1000),
    description1 nvarchar(1000),
    description2 nvarchar(1000),
    status int
);
-- select * from routetype
-- select * from routetypeItems
create table RouteTypeItems(
 id int primary key,
 name nvarchar(100),
 description nvarchar(500),
 routeTypeID  int,
 foreign key (RouteTypeID) references RouteType(id) ON DELETE CASCADE
);
create table Course(
	id int primary key,
    title nvarchar(100),
    numOfPeopleJoin nvarchar(100),
    price nvarchar(100),
    routeID int, 
    routeTypeItemsID int,
    isPublished boolean,
    foreign key (routeID) references RouteType(id) ON DELETE CASCADE,
    foreign key (routeTypeItemsID) references RouteTypeItems(id) 
);
create table RouteTypeItemAndCourse(
	id int primary key,
    RouteTypeItemID int,
    CourseID int,
    foreign key (RouteTypeItemID) references RouteTypeItems(id) ON DELETE CASCADE,
    foreign key (CourseID) references Course(id) ON DELETE CASCADE
);
create table CourseDetail(
	id int primary key,
    level nvarchar(100),
    sumLesson nvarchar(100),
    time nvarchar(100),
    detailCourseDes nvarchar(500),
    image nvarchar(100),
    courseID int,
	foreign key (courseID) references Course(id) ON DELETE CASCADE
);

create table CourseLearnWhat(
	id int primary key AUTO_INCREMENT, 
    content nvarchar(1000),
     courseID int,
	foreign key (courseID) references Course(id) ON DELETE CASCADE
);
create table CourseRequirement(
	id int primary key AUTO_INCREMENT, 
    content nvarchar(1000),
	courseID int,
	foreign key (courseID) references Course(id) ON DELETE CASCADE
);

create table State(
id int primary key,
state nvarchar(20)
);

create table CourseAccount(
	id int primary key,
    accountID int,
	courseID int,
    timeRegistration nvarchar(100),
    idState int,
    foreign key(idState) references State(id),
    foreign key (accountID) references Account(id),
	foreign key (courseID)	references Course(id) ON DELETE CASCADE
);
create table Chapter(
	id int primary key,
    name nvarchar(100),
    courseID int,
    foreign key (courseID)	references Course(id) ON DELETE CASCADE
);
create table Lesson(
	id int primary key,
    name nvarchar(100),
    content nvarchar(1000),
    link nvarchar(100),
    retry nvarchar(100),
    date nvarchar(100),
    numOfLikes nvarchar(100),
    type nvarchar(100),
    courseID int,
    chapterID int,
    foreign key (courseID)	references Course(id) ON DELETE CASCADE,
	foreign key (chapterID)	references Chapter(id) ON DELETE CASCADE
);
create table Question(
	id int primary key,
    content nvarchar(1000),
    `explain` nvarchar(1000)
);
create table LessonQuestion(
	id int primary key AUTO_INCREMENT,
    lessonID int,
    questionID int,
    foreign key (lessonID)	references Lesson(id) ON DELETE CASCADE,
    foreign key (questionID)	references Question(id) ON DELETE CASCADE
);
create table Answer(
	id int primary key,
    content nvarchar(500),
    quesID int,
    foreign key (quesID) references Question(id) ON DELETE CASCADE
);
create table CorrectAnswer(
	id int primary key,
    content nvarchar(500),
    quesID int,
	foreign key (quesID) references Question(id) ON DELETE CASCADE
);
create table AccountDoingQuestion(
	id int primary key, 
    answer nvarchar(500),
    trueOrFalse nvarchar(100),
    accountID int,
    quesID int,
    foreign key (accountID) references Account(id) ON DELETE CASCADE,
    foreign key (quesID) references Question(id) ON DELETE CASCADE
);
create table Note(
	id int primary key,
    detail LongText,
    date Date,
    lessonID int,
    accountID int,
	foreign key (lessonID)	references Lesson(id),
    foreign key (accountID)	references Account(id)
);
create table Notification(
	id int primary key AUTO_INCREMENT,
    Content text,
    url nvarchar(1024),
    fromAccount int,
    recieveAccount int,
    isRead bit,
    foreign key (fromAccount) references account(id),
    foreign key (recieveAccount) references account(id)
);

create table QAOfLesson(
id int AUTO_INCREMENT primary key,
content nvarchar(200),
date varchar(100),
userID int,
foreign key (userID) references Account(id),
lessonID int,
foreign key(lessonID) references Lesson(id)
);

create table ReplyQAOfLesson(
id int AUTO_INCREMENT primary key,
content nvarchar(200),
date varchar(100),
userID int,
foreign key (userID) references Account(id),
lessonID int,
foreign key(lessonID) references Lesson(id),
parentID int,
foreign key(parentID) references QAOfLesson(id)
);
-- Insert Setting table
insert into Setting(id, actor) Values(1, "Admin");
insert into Setting(id, actor) Values(2, "Customer");
insert into Setting(id, actor) Values(3, "Marketing");
insert into Setting(id, actor) Values(4, "Sales");
insert into Setting(id, actor) Values(5, "Expert");
-- Insert Account table
insert into Account(id, userName, password, mail, bio, avatar, url, verificationCode, registrationDate, firstAndLastName, coverImage, roleID)
Values(1, "dat310", "$2a$10$rBbHM187S9d2/pbh5zuu5egReFh.teIaLgk2yn2v8wgJzMXtX13Am", "datnguyen83.fpt@gmail.com", "Hoa rơi cửa phật , vạn sự tùy duyên", "https://i.pinimg.com/originals/e0/0e/d9/e00ed96cc83f6dc8d3b54e9fb63161d2.jpg", "https://www.facebook.com/sieusao.chemgio.90", 
null, NOW(), "Dat Cuu", "https://thuthuatnhanh.com/wp-content/uploads/2020/01/hinh-anh-nhung-chu-cho-de-thuong-nhat.jpg", 1);
insert into Account(id, userName, password,phone, mail, bio, avatar, url, verificationCode, registrationDate, firstAndLastName, coverImage, roleID)
Values(2, "tri", "$2a$10$rBbHM187S9d2/pbh5zuu5egReFh.teIaLgk2yn2v8wgJzMXtX13Am","0377069365", "trilee2002@gmail.com", "tokada", "https://media-cdn-v2.laodong.vn/Storage/NewsPortal/2020/8/21/829850/Bat-Cuoi-Truoc-Nhung-07.jpg", "https://www.facebook.com/junfoke", 
null, NOW(), "trapboiz37", "https://i.pinimg.com/originals/2a/73/4d/2a734da3d156164bfe7f2836b279737c.jpg", 2);
insert into Account(id, userName, password, mail, bio, avatar, url, verificationCode, registrationDate, firstAndLastName, coverImage, roleID)
Values(3, "dinhdung123", "$2a$10$rBbHM187S9d2/pbh5zuu5egReFh.teIaLgk2yn2v8wgJzMXtX13Am", "dinhdungviettel@gmail.com", "toiyeufpt", "https://mega.com.vn/media/news/0206_leesin.jpg", "https://www.facebook.com/tiendungg.1411", 
null, NOW(), "Mãi yêu tập đoàn FPT", "https://i.pinimg.com/736x/de/1b/d8/de1bd867f4055b61a2fa4bab10dc8930.jpg", 5);
-- Insert into table RouteType
insert into RouteType(id, name,image, description1, description2,status) Values(1, "Front-end-development","https://files.fullstack.edu.vn/f8-prod/learning-paths/2/63b4642136f3e.png","Lập trình viên Front-end là người xây dựng ra giao diện websites. Trong phần này F8 sẽ chia sẻ cho bạn lộ trình để trở thành lập trình viên Front-end nhé.", "Hầu hết các websites hoặc ứng dụng di động đều có 2 phần là Front-end và Back-end. Front-end là phần giao diện người dùng nhìn thấy và có thể tương tác, đó chính là các ứng dụng mobile hay những website bạn đã từng sử dụng. Vì vậy, nhiệm vụ của lập trình viên Front-end là xây dựng các giao diện đẹp, dễ sử dụng và tối ưu trải nghiệm người dùng. Tại Việt Nam, lương trung bình cho lập trình viên front-end vào khoảng 16.000.000đ / tháng. Dưới đây là các khóa học F8 đã tạo ra dành cho bất cứ ai theo đuổi sự nghiệp trở thành một lập trình viên Front-end. Các khóa học có thể chưa đầy đủ, F8 vẫn đang nỗ lực hoàn thiện trong thời gian sớm nhất.",0);
insert into RouteType(id, name,image, description1, description2,status) Values(2, "Back-end-development","	https://files.fullstack.edu.vn/f8-prod/learning-paths/3/63b4641535b16.png", "Trái với Front-end thì lập trình viên Back-end là người làm việc với dữ liệu, công việc thường nặng tính logic hơn. Chúng ta sẽ cùng tìm hiểu thêm về lộ trình học Back-end nhé.", "Hầu hết các websites hoặc ứng dụng di động đều có 2 phần là Front-end và Back-end. Front-end là phần giao diện người dùng nhìn thấy và có thể tương tác. Back-end là nơi xử lý dữ liệu và lưu trữ. Vì vậy, nhiệm vụ của lập trình viên Back-end là phân tích thiết kế dữ liệu, xử lý logic nghiệp vụ của các chức năng trong ứng dụng. Tại Việt Nam, lương trung bình cho lập trình viên Back-end vào khoảng 19.000.000đ / tháng. Dưới đây là các khóa học F8 đã tạo ra dành cho bất cứ ai theo đuổi sự nghiệp trở thành một lập trình viên Back-end. Các khóa học có thể chưa đầy đủ, F8 vẫn đang nỗ lực hoàn thiện trong thời gian sớm nhất.",0);

-- Insert into table RouteTypeItems
insert into RouteTypeItems(id, name,description,routeTypeID) Value(1, "Tìm hiểu về ngành IT","Để theo ngành IT - Phần mềm cần rèn luyện những kỹ năng nào? Bạn đã có sẵn tố chất phù hợp với ngành chưa? Cùng thăm quan các công ty IT và tìm hiểu về văn hóa, tác phong làm việc của ngành này nhé các bạn.", 1 );
insert into RouteTypeItems(id, name,description,routeTypeID) Value(2,"HTML và CSS","Để học web Front-end chúng ta luôn bắt đầu với ngôn ngữ HTML và CSS, đây là 2 ngôn ngữ có mặt trong mọi website trên internet. Trong khóa học này F8 sẽ chia sẻ từ những kiến thức cơ bản nhất. Sau khóa học này bạn sẽ tự làm được 2 giao diện websites là The Band và Shopee.",1);
insert into RouteTypeItems(id, name,description,routeTypeID) Value(3,"JavaScript","Với HTML, CSS bạn mới chỉ xây dựng được các websites tĩnh, chỉ bao gồm phần giao diện và gần như chưa có xử lý tương tác gì. Để thêm nhiều chức năng phong phú và tăng tính tương tác cho website bạn cần học Javascript.",1);
insert into RouteTypeItems(id, name,description,routeTypeID) Value(4,"Sử dụng Ubuntu/Linux","Cách làm việc với hệ điều hành Ubuntu/Linux qua Windows Terminal & WSL. Khi đi làm, nhiều trường hợp bạn cần nắm vững các dòng lệnh cơ bản của Ubuntu/Linux.",1);
insert into RouteTypeItems(id, name,description,routeTypeID) Value(5,"Libraries and Frameworks","Một websites hay ứng dụng hiện đại rất phức tạp, chỉ sử dụng HTML, CSS, Javascript theo cách code thuần (tự code từ đầu tới cuối) sẽ rất khó khăn. Vì vậy các Libraries, Frameworks ra đời nhằm đơn giản hóa, tiết kiệm chi phí và thời gian để hoàn thành một sản phẩm website hoặc ứng dụng mobile.",1);
insert into RouteTypeItems(id, name,description,routeTypeID) Value(6, "Tìm hiểu về ngành IT","Để theo ngành IT - Phần mềm cần rèn luyện những kỹ năng nào? Bạn đã có sẵn tố chất phù hợp với ngành chưa? Cùng thăm quan các công ty IT và tìm hiểu về văn hóa, tác phong làm việc của ngành này nhé các bạn.", 2 );
insert into RouteTypeItems(id, name,description,routeTypeID) Value(7,"HTML và CSS","Để học web Front-end chúng ta luôn bắt đầu với ngôn ngữ HTML và CSS, đây là 2 ngôn ngữ có mặt trong mọi website trên internet. Dù bạn có theo Back-end thì công việc của bạn nhiều khi vẫn cần phải ghép dữ liệu với HTML, CSS.",2);
insert into RouteTypeItems(id, name,description,routeTypeID) Value(8,"Ngôn ngữ lập trình","Có rất nhiều ngôn ngữ để bạn có thể làm việc với Back-end, tuy nhiên bạn không cần phải học tất cả. Bạn chỉ cần tập trung vào 1 ngôn ngữ là có thể làm việc tốt. Tại đây chúng ta sẽ bắt đầu với ngôn ngữ lập trình Javascript.",2);
insert into RouteTypeItems(id, name,description,routeTypeID) Value(9,"Sử dụng Ubuntu/Linux","Cách làm việc với hệ điều hành Ubuntu/Linux qua Windows Terminal & WSL. Khi đi làm, nhiều trường hợp bạn cần nắm vững các dòng lệnh cơ bản của Ubuntu/Linux.",2);
insert into RouteTypeItems(id, name,description,routeTypeID) Value(10,"Libraries and Frameworks","Một ứng dụng Back-end hiện đại có thể rất phức tạp, việc sử dụng code thuần (tự tay code từ đầu) không phải là một lựa chọn tốt. Vì vậy các Libraries và Frameworks ra đời nhằm đơn giản hóa, tiết kiệm thời gian và tiền bạc để nhanh chóng tạo ra được sản phẩm cuối cùng.",2);
-- Insert Course table
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(1, "HTML CSS từ Zero đến Hero", 1500, 0, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(2, "Lập trình JavaScript cơ bản", 1200, 0, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(3, "Xây Dựng Website với ReactJS", 1000, 0, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(4, "HTML CSS từ Zero đến Hero", 1500, 0, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(5, "Lập trình JavaScript cơ bản", 1200, 0, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(6, "Node & ExpressJS", 1000, 0, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(7, "HTML CSS Pro", 100, 1000000, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(8, "Responsive Với Grid System", 100, 0, null,null, true);
insert into Course(id, title, numOfPeopleJoin, price, routeID,routeTypeItemsID, isPublished) Values(9, "Kiến Thức Nhập Môn IT", 100, 0, null,null, true);

-- Insert CourseDetail Table
insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(1, "Easy", 20, "300", "Trong khóa này chúng ta sẽ cùng nhau xây dựng giao diện 2 trang web là The Band & Shopee.","https://files.fullstack.edu.vn/f8-prod/courses/2.png", 1);
insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(2, "Medium", 10, "150", "Học Javascript cơ bản phù hợp cho người chưa từng học lập trình. Với hơn 100 bài học và có bài tập thực hành sau mỗi bài học.","https://files.fullstack.edu.vn/f8-prod/courses/1.png", 2);
insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(3, "Hard", 15, "250", "Khóa học ReactJS từ cơ bản tới nâng cao, kết quả của khóa học này là bạn có thể làm hầu hết các dự án thường gặp với ReactJS.","https://files.fullstack.edu.vn/f8-prod/courses/13/13.png", 3);
insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(4, "Hard", 20, "300", "Học Back-end với Node & ExpressJS framework, hiểu các khái niệm khi làm Back-end và xây dựng RESTful API cho trang web.","https://files.fullstack.edu.vn/f8-prod/courses/6.png", 6);
insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(5, "Easy", 20, "300", "Cách dễ nhất để học HTML CSS cho người mới bắt đầu!","https://files.fullstack.edu.vn/f8-prod/courses/15/62f13d2424a47.png", 7);
insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(6, "Hard", 20, "300", "Trong khóa này chúng ta sẽ học về cách xây dựng giao diện web responsive với Grid System, tương tự Bootstrap 4.","https://files.fullstack.edu.vn/f8-prod/courses/3.png", 8);
insert into CourseDetail(id, level, sumLesson, time, detailCourseDes, image, courseID) Values(7, "Easy", 20, "300", "Để có cái nhìn tổng quan về ngành IT - Lập trình web các bạn nên xem các videos tại khóa này trước nhé.","https://files.fullstack.edu.vn/f8-prod/courses/7.png", 9);

-- Insert State table
insert into State(id, state) Values(1, "Đã thanh toán");
insert into State(id, state) Values(2, "Chưa thanh toán");
insert into State(id, state) Values(3, "Đã hủy");

-- Insert CourseAccount table
insert into CourseAccount(id, accountId, courseId, timeRegistration, idState) Values(1, 1, 1, NOW(), 1);
insert into CourseAccount(id, accountId, courseId, timeRegistration, idState) Values(2, 1, 2, NOW(),1);
insert into CourseAccount(id, accountId, courseId, timeRegistration, idState) Values(3, 1, 3, NOW(), 1);
-- select * from CourseAccount;

-- Insert Chapter Table
insert into Chapter(id, name, courseID) Values(1, "1. Bắt đầu", 1);
insert into Chapter(id, name, courseID) Values(2, "2. Làm quen với HTML", 1);
insert into Chapter(id, name, courseID) Values(3, "3. Làm quen với CSS", 1);
insert into Chapter(id, name, courseID) Values(4, "4. Đệm viền và khoang lề", 1);
insert into Chapter(id, name, courseID) Values(5, "5. Thuộc tính tạo nền", 1);

-- Insert Lesson Table
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(1, "Bạn sẽ làm được gì sau khóa học", null, "https://www.youtube.com/embed/R6plN3FvzFY", null, NOW(), 0, "video", 1, 1);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(2, "Tìm hiểu về HTML, CSS", null, "https://www.youtube.com/embed/zwsPND378OQ", null, NOW(), 0, "video", 1, 1);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(3, "Làm quen với Dev tools", null, "https://www.youtube.com/embed/7BJiPyN4zZ0", null, NOW(), 0, "video", 1, 1);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(4, "Cài đặt VS Code, Page Ruler, extension", null, "https://www.youtube.com/embed/ZotVkQDC6mU", null, NOW(), 0, "video", 1, 1);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(5, "Khắc phục lỗi cài đặt Page Ruler Redux", null, "https://www.youtube.com/embed/ZotVkQDC6mU", null, NOW(), 0, "video", 1, 1);

insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(6, "Cấu trúc của 1 file HTML", null, "https://www.youtube.com/embed/LYnrFSGLCl8", null, NOW(), 0, "video", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(7, "Làm quen với màn thử thách", null, "https://www.youtube.com/embed/JG0pdfdKjgQ", null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(8, "Ví dụ cấu trúc file HTML", null, "https://www.youtube.com/embed/AzmdwZ6e_aM", null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(9, "Comments trong HTML", null, "https://www.youtube.com/embed/UYpIh5pIkSA", null, NOW(), 0, "video", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(10, "Ví dụ comments trong HTML", null, "https://www.youtube.com/embed/4J6d8cr0X48", null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(11, "Các thẻ HTML thông dụng", null, "https://www.youtube.com/embed/NsSsJTg29oE", null, NOW(), 0, "video", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(12, "Ví dụ thẻ HTML thông dụng", null, "https://www.youtube.com/embed/aj-lD4XXr8A", null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(13, "Bài tập thẻ HTML thông dụng #1", null, "https://www.youtube.com/embed/VbzOimNAOxE", null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(14, "Bài tập thẻ HTML thông dụng #2", null, "https://www.youtube.com/embed/8X48l0CK5_4", null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(15, "Bài tập thẻ HTML thông dụng #3", null, "https://www.youtube.com/embed/bv16wjxgV4U", null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(16, "Attribute trong HTML là gì?", null, "https://www.youtube.com/embed/hMWhvbCJIq8", null, NOW(), 0, "video", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(17, "Bài tập sử dụng Attribute #1", "https://www.youtube.com/embed/bVUN6nS82k8", null, null, NOW(), 0, "practice", 1, 2);
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(18, "Cách quản lý thư mục dự án", null, "https://www.youtube.com/embed/k1ZH5Mlj3tw", null, NOW(), 0, "video", 1, 2);

-- feedbacklesson
insert into Lesson(id, name, content, link, retry, date, numOfLikes, type, courseID, chapterID) Values(19, "Feedback 1", null, "https://forms.gle/KpmKasmvmaBG1e4M6", null, NOW(), 0, "feedback", 1, 1);
-- Insert CourseLearnWhat Table
insert into CourseLearnWhat(id, content, courseID) Values(1, "Biết cách xây dựng giao diện web với HTML, CSS", 1);    
insert into CourseLearnWhat(id, content, courseID) Values(2, "Biết cách đặt tên class CSS theo chuẩn BEM", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(3, "Làm chủ Flexbox khi dựng bố cục website", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(4, "Biết cách tự tạo động lực cho bản thân", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(5, "Học được cách làm UI chỉn chu, kỹ tính", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(6, "Biết cách phân tích giao diện website", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(7, "Biết cách làm giao diện web responsive", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(8, "Sở hữu 2 giao diện web khi học xong khóa học", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(9, "Biết cách tự học những kiến thức mới", 1); 
insert into CourseLearnWhat(id, content, courseID) Values(10, "Nhận chứng chỉ khóa học do F8 cấp", 1); 


-- Insert Notifycation Table
INSERT INTO `db_f8`.`notification`
(`Content`,`url`,`fromAccount`,`recieveAccount`,`isRead`)
VALUES
('Đã tag bạn trong 1 bình luận', null,1,3,0);
INSERT INTO `db_f8`.`notification`
(`Content`,`url`,`fromAccount`,`recieveAccount`,`isRead`)
VALUES
('Đã tag bạn trong 1 bình luận', null,2,3,0);
INSERT INTO `db_f8`.`notification`
(`Content`,`url`,`fromAccount`,`recieveAccount`,`isRead`)
VALUES
('Đã nhắc đến bạn', null,2,1,0);
INSERT INTO `db_f8`.`notification`
(`Content`,`url`,`fromAccount`,`recieveAccount`,`isRead`)
VALUES
('Thông báo từ hệ thống', null,null,1,0);

-- Insert CourseRequirement Table
insert into CourseRequirement(id, content, courseID) Values(1, "Máy vi tính kết nối internet (Windows, Ubuntu hoặc MacOS)", 1);   
insert into CourseRequirement(id, content, courseID) Values(2, "Ý thức tự học cao, trách nhiệm cao, kiên trì bền bỉ không ngại cái khó", 1);   
insert into CourseRequirement(id, content, courseID) Values(3, "Không được nóng vội, bình tĩnh học, làm bài tập sau mỗi bài học", 1);   
insert into CourseRequirement(id, content, courseID) Values(4, "Khi học nếu có khúc mắc hãy tham gia hỏi/đáp tại group FB: Học lập trình web (fullstack.edu.vn)", 1);   
insert into CourseRequirement(id, content, courseID) Values(5, "Bạn không cần biết gì hơn nữa, trong khóa học tôi sẽ chỉ cho bạn những gì bạn cần phải biết", 1);   

-- INSERT INTO `db_f8`.`courseaccount` (`id`, `accountID`, `courseID`) VALUES ('1', '1', '1');
-- INSERT INTO `db_f8`.`courseaccount` (`id`, `accountID`, `courseID`) VALUES ('2', '1', '2');
-- INSERT INTO `db_f8`.`courseaccount` (`id`, `accountID`, `courseID`) VALUES ('3', '2', '3');
-- INSERT INTO `db_f8`.`courseaccount` (`id`, `accountID`, `courseID`) VALUES ('4', '3', '4');

-- insert into table RouteTypeItemAndCourse
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(1,1,9);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(2,2,1);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(3,3,2);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(4,4,6);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(5,5,3);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(6,6,9);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(7,7,1);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(8,7,7);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(9,8,2);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(10,9,8);
insert into RouteTypeItemAndCourse(id , RouteTypeItemID,CourseID ) values(11,10,3);

-- Insert into Blog table
insert into Blog(id, title, topic,stateID , content, numOfLikes, date, userID) Values(1, "Hoc nua hoc mai", null,1, "abc ab ah ba bababababb ababa", 0, NOW(), 1);
-- Insert into saved
-- Insert into Quiz
insert into Question(id, content, `explain`) Values(1, "1 + may = bao nhieu", "La bao nhie ma khong biet thi hoc lai nha!!!");

insert into Answer(id, content, quesID) Values(1, "10", 1);
insert into Answer(id, content, quesID) Values(2, "2 may", 1);
insert into Answer(id, content, quesID) Values(3, "chiu roi", 1);
insert into Answer(id, content, quesID) Values(4, "abc xyz", 1);

insert into CorrectAnswer(id, content, quesID) Values(1, "chiu roi", 1); 

insert into Question(id, content, `explain`) Values(2, "6 - 2 = ?", "Ly thuyet la vay roi!!!");

insert into Answer(id, content, quesID) Values(5, "1", 2);
insert into Answer(id, content, quesID) Values(6, "2", 2);
insert into Answer(id, content, quesID) Values(7, "3", 2);
insert into Answer(id, content, quesID) Values(8, "4", 2);

insert into CorrectAnswer(id, content, quesID) Values(2, "4", 2); 

insert into Question(id, content, `explain`) Values(3, "How many continent in the world?", "You can search gg.");

insert into Answer(id, content, quesID) Values(9, "5", 3);
insert into Answer(id, content, quesID) Values(10, "6", 3);
insert into Answer(id, content, quesID) Values(11, "7", 3);
insert into Answer(id, content, quesID) Values(12, "8", 3);

insert into CorrectAnswer(id, content, quesID) Values(3, "7", 3); 

insert into Question(id, content, `explain`) Values(4, "Can cu thi bu gi?", "Rat de ma, khong can phai giai thich dau ne`...");

insert into Answer(id, content, quesID) Values(13, "Sieng nang", 4);
insert into Answer(id, content, quesID) Values(14, "thong minh", 4);
insert into Answer(id, content, quesID) Values(15, "ko biet", 4);

insert into LessonQuestion(lessonID, questionID) Values(7, 1);
insert into LessonQuestion(lessonID, questionID) Values(7, 2);
insert into LessonQuestion(lessonID, questionID) Values(7, 3);
insert into LessonQuestion(lessonID, questionID) Values(8, 4);

insert into CorrectAnswer(id, content, quesID) Values(4, "thong minh", 4); 

-- use db_f8;
-- select * from Course c join CourseDetail cd on c.id = cd.courseID where c.price = 0;
-- select * from CourseDetail;
-- delete from CourseDetail where id > 5;
-- delete from Course where id > 7;
-- use db_f8;


-- select * from Course;

-- use db_f8;
-- select * from Course;
-- cho vao hashmap key=chapterId value la danh sach lesson co cung chapterId;
-- select * from Lesson;
-- select * from notification n join account a on n.fromAccount = a.id where recieveAccount = 3;
-- select * from Account
-- select c.id as cid, title, price, numOfPeopleJoin, routeID, cd.id as courseDetailID, 
 -- level, sumLesson, detailCourseDes, image, courseID from Course c join CourseDetail cd on c.id = cd.courseID where c.price = 0 
-- id int primary key,;

select * from course;
-- delete from course  where id = 1
-- select b.id, b.title, b.topic, b.content, b.numOfLikes, b.date, b.userID, a.avatar, a.userName from Blog b join Account a on b.userID = a.id where userID = 1
-- select * from Blog
select c.id, c.title, c.numOfPeopleJoin, cd.image, ca.timeRegistration from course c join courseaccount ca on c.id = ca.courseid
join courseDetail cd on c.id = cd.courseId where c.isPublished = true and ca.accountID = 1;
select * from CourseDetail;
select * from RouteType;
select * from RouteTypeItemAndCourse;
select * from RouteTypeItems;
-- select * from Question
-- select name from chapter
-- select * from CorrectAnswer
-- insert into question(id, content, lessonID) values(1, 'abc', 1)
-- insert into AccountDoingQuestion(id, answer, trueOrFalse, accountID, quesID) Values(1, 'chiu roi', 'true', '1', '1');
-- select * from AccountDoingQuestion
-- insert into AccountDoingQuestion()
-- delete from AccountDoingQuestion where id >=1

-- select q.id, c.title, l.name, q.content, q.explain from Question q join Lesson l on q.lessonID = l.id 

-- delete from routeType where id =2
-- select l.id, l.name, ct.name, c.title, q.id from lesson l join chapter ct on l.chapterID = ct.id join course c on ct.courseid = c.id join question q on l.id = q.lessonid
select * from courseDetails;
select b.id, b.title, b.topic, b.content, b.numOfLikes, b.date, b.userID, a.avatar, a.userName from Blog b join Account a on b.userID = a.id where b.stateId=1 and a.id=1
 
 