package yummy_ground.yummygyudon.obms.app.api;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.app.api.dto.request.BookRequest;
import yummy_ground.yummygyudon.obms.app.api.dto.response.BookResponse;

@Tag(
        name = "도서 관리 API",
        description = """
                온라인 도서 관리 시스템 중 <strong>도서 관리</strong> 기능을 수행하는 API입니다.<br/>
                * 본 API는 <strong>인증된 상태</strong>에서 사용 가능합니다.
                """
)
public interface BookApi {

    @Operation(
            summary = "도서 등록",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = BookRequest.BookRegister.class))
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "등록 성공",
                    content = @Content(schema = @Schema(contentMediaType = "application/json", implementation = BookResponse.BookDetail.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "올바르지 않은 Request Body 요청값"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "미인증 사용자의 요청"
            )
    })
    ResponseEntity<BaseResponse<?>> register(BookRequest.BookRegister newBook);

    @Operation(
            summary = "도서 상세 조회"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(contentMediaType = "application/json", implementation = BookResponse.BookDetail.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "올바르지 않은 Request Url Path 값"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "미인증 사용자의 요청"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 도서"
            )
    })
    ResponseEntity<BaseResponse<?>> getDetail(long bookId);

    @Operation(
            summary = "도서 목록 조회"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(contentMediaType = "application/json", implementation = BookResponse.BookAll.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "올바르지 않은 Request Parameter 값"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "미인증 사용자의 요청"
            )
    })
    ResponseEntity<BaseResponse<?>> getAll(String targetTitle, String targetAuthor, String sortBy, @Schema(hidden = true) Pageable pageable);

    @Operation(
            summary = "도서 수정",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = BookRequest.BookUpdate.class))
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "수정 성공",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "올바르지 않은 Request Url Path 값"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "올바르지 않은 Request Body 요청값"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "미인증 사용자의 요청"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 도서"
            )
    })
    ResponseEntity<BaseResponse<?>> update(long bookId, BookRequest.BookUpdate updatedBook);

    @Operation(
            summary = "도서 삭제"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "삭제 성공",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "올바르지 않은 Request Url Path 값"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "미인증 사용자의 요청"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 도서"
            )
    })
    ResponseEntity<BaseResponse<?>> delete(long bookId);

}
