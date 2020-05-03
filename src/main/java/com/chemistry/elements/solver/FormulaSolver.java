package com.chemistry.elements.solver;

import com.chemistry.elements.domain.Element;
import com.chemistry.elements.domain.FormulaPart;
import com.chemistry.elements.domain.FormulaType;
import com.chemistry.elements.domain.exception.EntityNotFoundException;
import com.chemistry.elements.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.*;

@Component
public class FormulaSolver {
    private final static String ELEMENT_NOT_EXIST = "Error: Element not exist: ";
    private final static String ILLEGAL_CHARACTER = "Error: Illegal character in formula: ";

    @Autowired
    ElementRepository repository;

    public double solver(String formula) {
        List<FormulaPart> formulaParts = splitFormula(formula);
        formulaParts = buildCanonical(formulaParts);

        System.out.println(formulaParts);

        return 0;
    }

    private List<FormulaPart> buildCanonical(List<FormulaPart> formulaParts) {
        List<FormulaPart> result = new ArrayList<>();

        if (formulaParts.size() > 1) {
            for (int i = 0; i < formulaParts.size() - 1; i++) {
                FormulaPart partA = formulaParts.get(i);
                FormulaPart partB = formulaParts.get(i + 1);
                FormulaPart partBetween = getPartBetween(partA, partB);

                result.add(partA);
                if (partBetween.getType() != FormulaType.NONE) result.add(partBetween);
                result.add(partB);
            }
        } else {
            result = formulaParts;
        }

        return result;
    }

    private List<FormulaPart> splitFormula(String formula) {
        int i = 0;
        char chr;
        List<FormulaPart> result = new ArrayList<>();

        while (i < formula.length()) {
            chr = formula.charAt(i);
            String value = "+";

            if (isLetter(chr)) {
                value = getSymbol(formula, i);
                Element element = getBySymbol(value);
                result.add(new FormulaPart(value, FormulaType.ELEMENT, element.getMass()));
            } else if(isDigit(chr)) {
                value = getValue(formula, i);
                result.add(new FormulaPart(value, FormulaType.VALUE, Double.parseDouble(value)));
            } else if (chr == '(') {
                result.add(new FormulaPart(String.valueOf(chr), FormulaType.LEFT_BRACKET, 0));
            } else if (chr == ')') {
                result.add(new FormulaPart(String.valueOf(chr), FormulaType.RIGHT_BRACKET, 0));
            } else if (chr == '*') {
                result.add(new FormulaPart(String.valueOf(chr), FormulaType.MULTIPLY, 0));
            } else if (chr == '+') {
                result.add(new FormulaPart(String.valueOf(chr), FormulaType.PLUS, 0));
            } else {
                throw new EntityNotFoundException(ILLEGAL_CHARACTER + chr);
            }

            i += value.length();
        }
        return result;
    }

    public Element getBySymbol(String symbol) {
        if (isUpperCase(symbol.charAt(0))) {
            return repository.findBySymbol(symbol).orElseThrow(() -> new EntityNotFoundException(ELEMENT_NOT_EXIST + symbol));
        } else {
            throw new EntityNotFoundException(ELEMENT_NOT_EXIST + symbol);
        }
    }

    private FormulaPart getPartBetween(FormulaPart partA, FormulaPart partB) {
        FormulaPart result;
        if (partA.getType() == FormulaType.ELEMENT && partB.getType() == FormulaType.ELEMENT) {
            result = new FormulaPart("+", FormulaType.PLUS, 0);
        } else if (partA.getType() == FormulaType.ELEMENT && partB.getType() == FormulaType.VALUE) {
            result = new FormulaPart("*", FormulaType.MULTIPLY, 0);
        } else if (partA.getType() == FormulaType.VALUE && partB.getType() == FormulaType.ELEMENT) {
            result = new FormulaPart("+", FormulaType.PLUS, 0);
        } else if (partA.getType() == FormulaType.ELEMENT && partB.getType() == FormulaType.LEFT_BRACKET) {
            result = new FormulaPart("+", FormulaType.PLUS, 0);
        } else if (partA.getType() == FormulaType.RIGHT_BRACKET && partB.getType() == FormulaType.ELEMENT) {
            result = new FormulaPart("+", FormulaType.PLUS, 0);
        } else if (partA.getType() == FormulaType.RIGHT_BRACKET && partB.getType() == FormulaType.VALUE) {
            result = new FormulaPart("*", FormulaType.MULTIPLY, 0);
        } else if (partA.getType() == FormulaType.RIGHT_BRACKET && partB.getType() == FormulaType.LEFT_BRACKET) {
            result = new FormulaPart("+", FormulaType.PLUS, 0);
        } else {
            result = new FormulaPart("", FormulaType.NONE, 0);
        }
        return result;
    }

    private String getSymbol(String formula, int i) {
        StringBuilder result = new StringBuilder();
        result.append(formula.charAt(i));

        i++;
        if (i < formula.length()) {
            char chr = formula.charAt(i);
            if (isLowerCase(chr)) {
                result.append(formula.charAt(i));
            }
        }
        return result.toString();
    }

    private String getValue(String formula, int i) {
        StringBuilder result = new StringBuilder();

        char chr = formula.charAt(i);
        while (isDigit(chr)) {
            result.append(chr);
            i++;
            if (i >= formula.length()) break;
            chr = formula.charAt(i);
        }
        return result.toString();
    }
}
