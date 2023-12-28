import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { TradeDetailComponent } from './trade-detail.component';

describe('Trade Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TradeDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: TradeDetailComponent,
              resolve: { trade: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TradeDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load trade on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TradeDetailComponent);

      // THEN
      expect(instance.trade).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
