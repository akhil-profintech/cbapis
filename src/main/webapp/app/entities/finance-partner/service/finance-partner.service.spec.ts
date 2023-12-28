import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFinancePartner } from '../finance-partner.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../finance-partner.test-samples';

import { FinancePartnerService } from './finance-partner.service';

const requireRestSample: IFinancePartner = {
  ...sampleWithRequiredData,
};

describe('FinancePartner Service', () => {
  let service: FinancePartnerService;
  let httpMock: HttpTestingController;
  let expectedResult: IFinancePartner | IFinancePartner[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FinancePartnerService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a FinancePartner', () => {
      const financePartner = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(financePartner).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FinancePartner', () => {
      const financePartner = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(financePartner).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FinancePartner', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FinancePartner', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FinancePartner', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFinancePartnerToCollectionIfMissing', () => {
      it('should add a FinancePartner to an empty array', () => {
        const financePartner: IFinancePartner = sampleWithRequiredData;
        expectedResult = service.addFinancePartnerToCollectionIfMissing([], financePartner);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(financePartner);
      });

      it('should not add a FinancePartner to an array that contains it', () => {
        const financePartner: IFinancePartner = sampleWithRequiredData;
        const financePartnerCollection: IFinancePartner[] = [
          {
            ...financePartner,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFinancePartnerToCollectionIfMissing(financePartnerCollection, financePartner);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FinancePartner to an array that doesn't contain it", () => {
        const financePartner: IFinancePartner = sampleWithRequiredData;
        const financePartnerCollection: IFinancePartner[] = [sampleWithPartialData];
        expectedResult = service.addFinancePartnerToCollectionIfMissing(financePartnerCollection, financePartner);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(financePartner);
      });

      it('should add only unique FinancePartner to an array', () => {
        const financePartnerArray: IFinancePartner[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const financePartnerCollection: IFinancePartner[] = [sampleWithRequiredData];
        expectedResult = service.addFinancePartnerToCollectionIfMissing(financePartnerCollection, ...financePartnerArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const financePartner: IFinancePartner = sampleWithRequiredData;
        const financePartner2: IFinancePartner = sampleWithPartialData;
        expectedResult = service.addFinancePartnerToCollectionIfMissing([], financePartner, financePartner2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(financePartner);
        expect(expectedResult).toContain(financePartner2);
      });

      it('should accept null and undefined values', () => {
        const financePartner: IFinancePartner = sampleWithRequiredData;
        expectedResult = service.addFinancePartnerToCollectionIfMissing([], null, financePartner, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(financePartner);
      });

      it('should return initial array if no FinancePartner is added', () => {
        const financePartnerCollection: IFinancePartner[] = [sampleWithRequiredData];
        expectedResult = service.addFinancePartnerToCollectionIfMissing(financePartnerCollection, undefined, null);
        expect(expectedResult).toEqual(financePartnerCollection);
      });
    });

    describe('compareFinancePartner', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFinancePartner(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFinancePartner(entity1, entity2);
        const compareResult2 = service.compareFinancePartner(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFinancePartner(entity1, entity2);
        const compareResult2 = service.compareFinancePartner(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFinancePartner(entity1, entity2);
        const compareResult2 = service.compareFinancePartner(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
