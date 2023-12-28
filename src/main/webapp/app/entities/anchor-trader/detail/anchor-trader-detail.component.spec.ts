import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { AnchorTraderDetailComponent } from './anchor-trader-detail.component';

describe('AnchorTrader Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnchorTraderDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: AnchorTraderDetailComponent,
              resolve: { anchorTrader: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(AnchorTraderDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load anchorTrader on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', AnchorTraderDetailComponent);

      // THEN
      expect(instance.anchorTrader).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
