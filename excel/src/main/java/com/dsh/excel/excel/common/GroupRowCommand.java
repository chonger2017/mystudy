package com.dsh.excel.excel.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.area.Area;
import org.jxls.command.AbstractCommand;
import org.jxls.command.Command;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.common.Size;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.Util;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_19:25
 */
public class GroupRowCommand extends AbstractCommand {
    Area area;
    String collapseIf;

    @Override
    public String getName() {
        return "groupRow";
    }

    @Override
    public Size applyAt(CellRef cellRef, Context context) {
        Size resultSize = area.applyAt(cellRef, context);
        if( resultSize.equals(Size.ZERO_SIZE)) return resultSize;
        PoiTransformer transformer = (PoiTransformer) area.getTransformer();
        Workbook workbook = transformer.getWorkbook();
        Sheet sheet = workbook.getSheet(cellRef.getSheetName());
        int startRow = cellRef.getRow();
        int endRow = cellRef.getRow() + resultSize.getHeight() - 1;
        sheet.groupRow(startRow, endRow);
        if(StringUtils.isNotBlank(collapseIf)){
            boolean collapseFlag = Util.isConditionTrue(getTransformationConfig().getExpressionEvaluator(), collapseIf, context);
            sheet.setRowGroupCollapsed(startRow, collapseFlag);
        }
        return resultSize;
    }

    @Override
    public Command addArea(Area area) {
        super.addArea(area);
        this.area = area;
        return this;
    }

    public void setCollapseIf(String collapseIf) {
        this.collapseIf = collapseIf;
    }
}
