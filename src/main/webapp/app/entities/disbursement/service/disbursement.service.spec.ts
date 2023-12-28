import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IDisbursement } from '../disbursement.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../disbursement.test-samples';

import { DisbursementService, RestDisbursement } from './disbursement.service';

const requireRestSample: RestDisbursement = {
  ...sampleWithRequiredData,
  offerAcceptedDate: sampleWithRequiredData.offerAcceptedDate?.format(DATE_FORMAT),
  dbmtRequestDate: sampleWithRequiredData.dbmtRequestDate?.format(DATE_FORMAT),
};

describe('Disbursement Service', () => {
  let service: DisbursementService;
  let httpMock: HttpTestingController;
  let expectedResult: IDisbursement | IDisbursement[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DisbursementService);
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

    it('should create a Disbursement', () => {
      const disbursement = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(disbursement).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Disbursement', () => {
      const disbursement = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(disbursement).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Disbursement', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Disbursement', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Disbursement', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDisbursementToCollectionIfMissing', () => {
      it('should add a Disbursement to an empty array', () => {
        const disbursement: IDisbursement = sampleWithRequiredData;
        expectedResult = service.addDisbursementToCollectionIfMissing([], disbursement);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(disbursement);
      });

      it('should not add a Disbursement to an array that contains it', () => {
        const disbursement: IDisbursement = sampleWithRequiredData;
        const disbursementCollection: IDisbursement[] = [
          {
            ...disbursement,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDisbursementToCollectionIfMissing(disbursementCollection, disbursement);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Disbursement to an array that doesn't contain it", () => {
        const disbursement: IDisbursement = sampleWithRequiredData;
        const disbursementCollection: IDisbursement[] = [sampleWithPartialData];
        expectedResult = service.addDisbursementToCollectionIfMissing(disbursementCollection, disbursement);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(disbursement);
      });

      it('should add only unique Disbursement to an array', () => {
        const disbursementArray: IDisbursement[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const disbursementCollection: IDisbursement[] = [sampleWithRequiredData];
        expectedResult = service.addDisbursementToCollectionIfMissing(disbursementCollection, ...disbursementArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const disbursement: IDisbursement = sampleWithRequiredData;
        const disbursement2: IDisbursement = sampleWithPartialData;
        expectedResult = service.addDisbursementToCollectionIfMissing([], disbursement, disbursement2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(disbursement);
        expect(expectedResult).toContain(disbursement2);
      });

      it('should accept null and undefined values', () => {
        const disbursement: IDisbursement = sampleWithRequiredData;
        expectedResult = service.addDisbursementToCollectionIfMissing([], null, disbursement, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(disbursement);
      });

      it('should return initial array if no Disbursement is added', () => {
        const disbursementCollection: IDisbursement[] = [sampleWithRequiredData];
        expectedResult = service.addDisbursementToCollectionIfMissing(disbursementCollection, undefined, null);
        expect(expectedResult).toEqual(disbursementCollection);
      });
    });

    describe('compareDisbursement', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDisbursement(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareDisbursement(entity1, entity2);
        const compareResult2 = service.compareDisbursement(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareDisbursement(entity1, entity2);
        const compareResult2 = service.compareDisbursement(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareDisbursement(entity1, entity2);
        const compareResult2 = service.compareDisbursement(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
