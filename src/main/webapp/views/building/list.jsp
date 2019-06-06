<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách tòa nhà</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
						
				
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-box table-filter">
							<div class="widget-header">
								<h4 class="widget-title">Tìm kiếm</h4>
								<div class="widget-toolbar">
									<a href="#" data-action="collapse"> <i
										class="ace-icon fa fa-chevron-up"></i>
									</a>
								</div>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<div class="form-horizontal">

										<div class="form-group">
											<div class="col-sm-6">
												<label>Tên Sản phẩm</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-6">
												<label>Diện tích sàn</label>
												<div class="fg-line">
													<input type="number" class="form-control input-sm" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-4">
												<label for="sel1">Quận hiện có</label> <select
													class="form-control" id="sel1">
													<option>-Chọn quận-</option>
													<option>2</option>
													<option>3</option>
													<option>4</option>
												</select>
											</div>
											<div class="col-sm-4">
												<label>Phường</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Đường</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-4">
												<label>Số tầng hầm</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Hướng</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Hạng</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-3">
												<label>Tên quản lý</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>Điện thoại quản lý</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>Giá thuê từ</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-3">
												<label>Giá thuê đến</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-4">
												<label>Tên quản lý</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label>Điện thoại quản lý</label>
												<div class="fg-line">
													<input type="text" class="form-control input-sm" />
												</div>
											</div>
											<div class="col-sm-4">
												<label for="sel1">Chọn nhân viên phụ trách</label> <select
													class="form-control" id="sel1">
													<option>-Chọn nhân viên phụ trách-</option>
													<option>2</option>
													<option>3</option>
													<option>4</option>
												</select>
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-12">
												<div class="checkbox-inline">
													<label><input type="checkbox" value="">Tầng
														trệt</label>
												</div>
												<div class="checkbox-inline">
													<label><input type="checkbox" value="">Nguyên
														căn</label>
												</div>
												<div class="checkbox-inline disabled">
													<label><input type="checkbox" value="" disabled>Nội
														thất</label>
												</div>
											</div>

										</div>
										<div class="form-group">
											<div class="col-sm-12">
												<button type="button" class="btn btn-success">Tìm
													kiếm-></button>
											</div>

										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
</body>
</html>