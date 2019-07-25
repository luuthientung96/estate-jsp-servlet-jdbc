<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api-admin-building" />
<c:url var="buildingURL" value="/admin-building" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cập nhật tòa nhà</title>
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
				<form id="formEdit">
					<div class="form-group distance">
						<label class="col-sm-2">Tên tòa nhà</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="name" id="name"
								value="${model.name}" />
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Người quản lý sản phẩm</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Quận</label>
						<div class="col-sm-10">
							<select class="form-control" id="sel1" name="district">
								<option value="">Chọn Quận</option>
								<c:forEach var="item" items="${districts}">
									<option value="${item.key}"
										${item.key==model.district?'selected':''}>${item.value}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Phường</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="ward"
								value="${model.ward}" />
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Đường</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="street"
								value="${model.street}" />
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Kết cấu</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Số tầng hầm</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="numberOfBasement"
								value="${model.numberOfBasement}" />
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Diện tích sàn</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="buildingArea"
								value="${model.buildingArea}" id="buildingArea">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Hướng</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="direction"
								value="${model.direction}">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Hạng</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="level"
								value="${model.level}">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Diện tích thuê</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="rentArea"
								value="${model.rentArea}">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Mô tả diện tích</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Giá thuê</label>
						<div class="col-sm-10">
							<input type="number" class="form-control" name="costRent"
								value="${model.costRent}">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Mô tả giá</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Phí dịch vụ</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Phí ô tô</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Phí mô tô</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Phí ngoài giờ</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Tiền điện</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Đặt cọc</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Thanh toán</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Thời hạn thuê</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Thời gian trang trí</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Tên quản lý</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">SĐT quản lý</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="managerName"
								value="${model.managerName}">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Phí mô giới</label>
						<div class="col-sm-10">
							<input type="text" class="form-control">
						</div>
					</div>
					<div class="form-group distance">
						<label class="col-sm-2">Loại tòa nhà</label>
						<div class="col-sm-10">
							<c:forEach var="item" items="${buildingTypes}">
								<label><input type="checkbox" name="buildingTypes"
									value="${item.key}"
									${fn:contains(fn:join(model.buildingTypes,','),item.key)?'checked':''}>${item.value}
								</label>
							</c:forEach>
						</div>
					</div>
					<input type="hidden" name="id" value="${model.id}" id="buildingId">
				</form>

				<div class="row text-center btn-addsp">
					<c:if test="${empty model.id }">
						<button type="submit" id="btnAddOrUpdateBuilding"
							class="btn btn-success">Thêm tòa nhà</button>
					</c:if>
					<c:if test="${not empty model.id }">
						<button type="submit" id="btnAddOrUpdateBuilding"
							class="btn btn-success">Cập nhật tòa nhà</button>
					</c:if>
				</div>

			</div>
		</div>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		$("#btnAddOrUpdateBuilding").click(function() {
			addOrUpdateBuilding();
		});

		function addOrUpdateBuilding() {
			var buildingId = $('#buildingId').val();
			var formData = $('#formEdit').serializeArray();//lấy giá trị từ formData
			var data = {};
			var buildingTypes = [];
			$.each(formData, function(index, v) {
				if (v.name == 'buildingTypes') {
					buildingTypes.push(v.value);
				}
				data["" + v.name + ""] = v.value;
			});
			data['buildingTypes'] = buildingTypes;

			if (buildingId == '') {
				addBuilding(data);
			} else {
				editBuilding(data, buildingId);
			}
		}

		function addBuilding(data) {
			$.ajax({
						url : '${buildingAPI}',//link tới api-admin-building
						data : JSON.stringify(data),//dữ liệu gửi lên api chuyển về JSON vì khi ở ngoài Client mọi thứ đều là object
						type : 'POST',
						contentType : 'application/json',//đẩy dữ liệu từ Client qua Server để hiểu data là JSON
						dataType : 'json',//Gửi từ Server lên Client để hiểu data là Json
						success : function(data) {
							//Khi thêm thành công thì load lại trang EDIT đưa ra thông báo thêm thành công
							window.location.href = "${buildingURL}?action=EDIT&id="
									+ data.id + "&message=insert_success";

						},
						error : function() {
							//Đưa ra thông báo lỗi nếu không thêm thành công
							window.location.href = "${buildingURL}?action=LIST&message=error_system";
						},
					});
		}
		function editBuilding(data,id) {
			$.ajax({
				url : '${buildingAPI}',//link tới api-admin-building
				data : JSON.stringify(data),//dữ liệu gửi lên api chuyển về JSON vì khi ở ngoài Client mọi thứ đều là object
				type : 'PUT',
				contentType : 'application/json',//đẩy dữ liệu từ Client qua Server để hiểu data là JSON
				success : function(data) {
					//Khi Update thành công thì load lại trang EDIT đưa ra thông báo update thành công
					window.location.href = "${buildingURL}?action=EDIT&id="+id+"&message=update_success";

				},
				error : function() {
					//Đưa ra thông báo lỗi nếu không thêm thành công
					window.location.href = "${buildingURL}?action=LIST&message=error_system";
				},
			});
		}
	</script>
</body>