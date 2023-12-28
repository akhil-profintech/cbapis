import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IRepayment } from '../repayment.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../repayment.test-samples';

import { RepaymentService, RestRepayment } from './repayment.service';

const requireRestSample: RestRepayment = {
  ...sampleWithRequiredData,
  offerAcceptedDate: sampleWithRequiredData.offerAcceptedDate?.format(DATE_FORMAT),
  repaymentDate: sampleWithRequiredData.repaymentDate?.format(DATE_FORMAT),
  repaymentDueDate: sampleWithRequiredData.repaymentDueDate?.format(DATE_FORMAT),
};

describe('Repayment Service', () => {
  let service: RepaymentService;
  let httpMock: HttpTestingController;
  let expectedResult: IRepayment | IRepayment[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RepaymentService);
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

    it('should create a Repayment', () => {
      const repayment = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(repayment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Repayment', () => {
      const repayment = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(repayment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Repayment', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Repayment', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Repayment', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRepaymentToCollectionIfMissing', () => {
      it('should add a Repayment to an empty array', () => {
        const repayment: IRepayment = sampleWithRequiredData;
        expectedResult = service.addRepaymentToCollectionIfMissing([], repayment);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(repayment);
      });

      it('should not add a Repayment to an array that contains it', () => {
        const repayment: IRepayment = sampleWithRequiredData;
        const repaymentCollection: IRepayment[] = [
          {
            ...repayment,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRepaymentToCollectionIfMissing(repaymentCollection, repayment);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Repayment to an array that doesn't contain it", () => {
        const repayment: IRepayment = sampleWithRequiredData;
        const repaymentCollection: IRepayment[] = [sampleWithPartialData];
        expectedResult = service.addRepaymentToCollectionIfMissing(repaymentCollection, repayment);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(repayment);
      });

      it('should add only unique Repayment to an array', () => {
        const repaymentArray: IRepayment[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const repaymentCollection: IRepayment[] = [sampleWithRequiredData];
        expectedResult = service.addRepaymentToCollectionIfMissing(repaymentCollection, ...repaymentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const repayment: IRepayment = sampleWithRequiredData;
        const repayment2: IRepayment = sampleWithPartialData;
        expectedResult = service.addRepaymentToCollectionIfMissing([], repayment, repayment2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(repayment);
        expect(expectedResult).toContain(repayment2);
      });

      it('should accept null and undefined values', () => {
        const repayment: IRepayment = sampleWithRequiredData;
        expectedResult = service.addRepaymentToCollectionIfMissing([], null, repayment, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(repayment);
      });

      it('should return initial array if no Repayment is added', () => {
        const repaymentCollection: IRepayment[] = [sampleWithRequiredData];
        expectedResult = service.addRepaymentToCollectionIfMissing(repaymentCollection, undefined, null);
        expect(expectedResult).toEqual(repaymentCollection);
      });
    });

    describe('compareRepayment', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRepayment(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareRepayment(entity1, entity2);
        const compareResult2 = service.compareRepayment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareRepayment(entity1, entity2);
        const compareResult2 = service.compareRepayment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareRepayment(entity1, entity2);
        const compareResult2 = service.compareRepayment(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
