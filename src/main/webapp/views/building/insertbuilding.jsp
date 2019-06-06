<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
					<li>Danh sách sản phẩm</li>
					<li>Chỉnh sửa sản phẩm</li>

				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<form class="form-horizontal" action="">
							<div class="form-group">
								<label class="col-sm-2">Tên sản phẩm</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Người quản lý sản phẩm</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Quận</label>
								<div class="col-sm-4">
									<select class="form-control" id="sel1">
										<option>-Chọn quận-</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Phường</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Đường</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Kết cấu</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Số tầng hầm</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Diện tích sàn</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Hướng</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Hạng</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Diện tích thuê</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Mô tả diện tích</label>
								<div class="col-sm-10">
									<input type="textarea" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Giá thuê</label>
								<div class="col-sm-10">
									<input type="number" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Mô tả giá</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Phí dịch vụ</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Phí ô tô</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Phí mô tô</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Phí ngoài giờ</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Tiền điện</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Đặt cọc</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Thanh toán</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Thời hạn thuê</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Thời gian trang trí</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Tên quản lý</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">SĐT quản lý</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Phí mô giới</label>
								<div class="col-sm-10">
									<input type="text" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Loại sản phẩm</label>
								<div class="col-sm-10">
									<div class="checkbox">
										<label><input type="checkbox" value="">Tầng trệt</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="">Nguyên căn</label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value="">Nội thất</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2">Thêm hình ảnh</label>
								<div class="col-sm-4">
									<input type="file" class="form-control">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
</body>