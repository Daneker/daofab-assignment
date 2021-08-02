package com.example.assignment.controller.v1;

import com.example.assignment.controller.V1BaseController;
import com.example.assignment.model.Child;
import com.example.assignment.model.Parent;
import com.example.assignment.model.pojo.Page;
import com.example.assignment.model.pojo.Pagination;
import com.example.assignment.service.ChildService;
import com.example.assignment.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionsController extends V1BaseController {

    private static final String PARENT_TRANSACTIONS = "/transactions";
    private static final String CHILD_TRANSACTIONS = PARENT_TRANSACTIONS + "/{parentId}";

    private final ParentService parentService;
    private final ChildService childService;


    /**
     * @api {get} /transactions   Get all parent transactions
     * @apiName ParentTransactions
     * @apiVersion 0.1.0
     *
     * @apiParam {int} [page=1] Page number
     * @apiParam {int} [size=10] Item count per page
     * @apiParam {String} [sort-by]  Sort by custom column & order type separated by ":"
     *
     * @apiParamExample Request parent transactions sorted ascending by id
     * http://host:port/api/v1/transactions?sort-by=id:asc
     *
     * @apiSuccessExample {json} Success-Response:
     *     HTTP/1.1 200 OK
     *     X-Total-Count: 7
     *     [
     *          {
     *              "id": 1,
     *              "sender": "ABC",
     *              "receiver": "XYZ",
     *              "totalAmount": 200,
     *              "totalPaidAmount": 100
     *          },
     *          {
     *              "id": 2,
     *              "sender": "XYZ",
     *              "receiver": "MNP",
     *              "totalAmount": 100,
     *              "totalPaidAmount": 100
     *          }
     *      ]
     */
    @GetMapping(PARENT_TRANSACTIONS)
    public List<Parent> listAll(@Valid @ModelAttribute Pagination pagination,
                             @RequestParam(name = "sort-by") @Nullable String orderBy,
                             HttpServletResponse response) {

        Page<Parent> page = parentService.getList(pagination, orderBy);
        response.addHeader(V1BaseController.TOTAL_COUNT, String.valueOf(page.getTotal()));

        return page.getContent();
    }

    /**
     * @api {get} /transactions/:parent_id Get by parent id
     * @apiName ChildTransactions
     * @apiVersion 0.1.0
     *
     * Example: http://host:port/api/v1/transactions/1
     *
     * @apiSuccessExample {json} Success-Response:
     *     HTTP/1.1 200 OK
     *     [
     *          {
     *              "id": 1,
     *              "sender": "ABC",
     *              "receiver": "XYZ",
     *              "totalAmount": 200,
     *              "paidAmount": 10
     *          },
     *          {
     *              "id": 2,
     *              "sender": "ABC",
     *              "receiver": "XYZ",
     *              "totalAmount": 200,
     *              "paidAmount": 50
     *          },
     *          {
     *              "id": 3,
     *              "sender": "ABC",
     *              "receiver": "XYZ",
     *              "totalAmount": 200,
     *              "paidAmount": 40
     *          }
     *      ]
     */
    @GetMapping(CHILD_TRANSACTIONS)
    public List<Child> listChildTransactionsByParentId(@PathVariable String parentId) {
        return childService.getList(Long.parseLong(parentId));
    }
}
